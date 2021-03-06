package joist.sourcegen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import joist.util.Copy;
import joist.util.Interpolate;
import joist.util.Read;
import joist.util.Write;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GDirectory {

  private final File directory;
  private final List<GClass> classes = new ArrayList<GClass>();
  private final List<File> touched = new ArrayList<File>();

  public GDirectory(String directory) {
    this.directory = new File(directory);
  }

  public GClass getClass(String _fullClassName, Object... args) {
    String fullClassName = Interpolate.string(_fullClassName, args);
    for (GClass gc : this.classes) {
      if (gc.isSameClass(fullClassName)) {
        return gc;
      }
    }
    GClass gc = new GClass(fullClassName);
    this.classes.add(gc);
    return gc;
  }

  public boolean exists(String fullClassName) {
    return this.getFile(fullClassName).exists();
  }

  public void output() {
    for (GClass gc : this.classes) {
      String newCode = gc.toCode();

      File file = this.getFile(gc);
      if (file.exists()) {
        String existingCode = Read.fromFile(file);
        if (newCode.equals(existingCode)) {
          this.touched.add(file);
          continue;
        }
      }

      file.getParentFile().mkdirs();
      log.info("Saving {}", file);
      Write.toFile(file, newCode);
      this.touched.add(file);
    }
  }

  public void pruneIfNotTouched() {
    for (File dir : this.getAllDirectories()) {
      for (File file : dir.listFiles()) {
        if (file.isFile() && !this.touched.contains(file)) {
          log.warn("Removing old file {}", file);
          file.delete();
        }
      }
    }
  }

  public void pruneIfNotTouchedWithinUsedPackages() {
    for (File dir : this.getUsedDirectories()) {
      for (File file : dir.listFiles()) {
        if (file.isFile() && !this.touched.contains(file)) {
          log.warn("Removing old file {}", file);
          file.delete();
        }
      }
    }
  }

  /** @returns only directories that we've output classes into, so that we conceptually "own" */
  private List<File> getUsedDirectories() {
    List<File> used = Copy.list();
    for (File dir : this.getAllDirectories()) {
      boolean foundTouchedFileInThisDir = false;
      for (File touched : this.touched) {
        if (touched.getParentFile().equals(dir)) {
          foundTouchedFileInThisDir = true;
          break;
        }
      }
      if (foundTouchedFileInThisDir) {
        used.add(dir);
      }
    }
    return used;
  }

  /** @returns all of the directories starting at {@code this.directory}. */
  private List<File> getAllDirectories() {
    List<File> directories = Copy.list();
    List<File> directoriesToCheck = Copy.list(this.directory);
    while (directoriesToCheck.size() > 0) {
      File dir = directoriesToCheck.remove(0);
      for (File file : dir.listFiles()) {
        if (file.isDirectory()) {
          directories.add(file);
          directoriesToCheck.add(file);
        }
      }
    }
    return directories;
  }

  private File getFile(GClass gc) {
    return this.getFile(gc.getFullName());
  }

  private File getFile(String fullClassName) {
    return new File(this.directory, fullClassName.replace('.', File.separatorChar) + ".java");
  }

}
