package joist.codegen.passes;

import joist.codegen.Codegen;
import joist.codegen.InformationSchemaColumn;
import joist.codegen.dtos.Entity;
import joist.codegen.dtos.ManyToManyProperty;
import joist.codegen.dtos.ManyToOneProperty;

public class FindManyToManyPropertiesPass implements Pass {

  public void pass(Codegen codegen) {
    for (InformationSchemaColumn column : codegen.getColumns()) {
      if (column.foreignKeyConstraintName == null || !codegen.isManyToManyTable(column)) {
        continue;
      }

      Entity joinTable = codegen.getEntity(column.tableName);

      for (ManyToOneProperty mtop : joinTable.getManyToOneProperties()) {
        mtop.getOneToManyProperty().setManyToMany(true);
      }

      Entity mySide = codegen.getEntity(column.foreignKeyTableName);
      if (mySide == null) {
        throw new RuntimeException("Could not find entity " + column.foreignKeyTableName);
      }

      Entity otherSide = codegen.getEntity(this.findOtherColumnInTable(codegen, column).foreignKeyTableName);
      if (otherSide == null) {
        throw new RuntimeException("Could not find entity " + column.tableName);
      }

      ManyToManyProperty mtmp = new ManyToManyProperty(codegen, joinTable, mySide, otherSide, column);
      mySide.getManyToManyProperties().add(mtmp);

      // Go find our corresponding property--if it is there yet (of 2, last one adds both)
      for (ManyToManyProperty other : otherSide.getManyToManyProperties()) {
        if (other.getJoinTable().getTableName().equals(mtmp.getJoinTable().getTableName()) && other != mtmp) {
          other.setOther(mtmp);
          mtmp.setOther(other);
        }
      }
    }
  }

  private InformationSchemaColumn findOtherColumnInTable(Codegen codegen, InformationSchemaColumn column1) {
    for (InformationSchemaColumn column2 : codegen.getColumns()) {
      if (column2.foreignKeyConstraintName != null //
        && column2.tableName.equals(column1.tableName)
        && !column2.name.equals(column1.name)) {
        return column2;
      }
    }
    throw new RuntimeException("Other column in many to many table " + column1.tableName + " not found");
  }

}
