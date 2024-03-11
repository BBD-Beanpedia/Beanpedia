-- liquibase formatted sql

-- changeset lJordaan:create-table-beantype
CREATE TABLE "BeanType"
(
  "TypeId" serial PRIMARY KEY,
  "BeanType" varchar(50) UNIQUE,
  "Description" varchar(500)
)
-- rollback DROP TABLE "BeanType"

-- changeset lJordaan:create-table-beanshape
CREATE TABLE "BeanShape"
(
  "ShapeId" serial PRIMARY KEY,
  "Shape" varchar(50) UNIQUE,
  "Description" varchar(500)
)
-- rollback DROP TABLE "BeanShape"

-- changeset lJordaan:create-table-beanorigin
CREATE TABLE "BeanOrigin"
(
  "OriginId" serial PRIMARY KEY,
  "Origin" varchar(20) UNIQUE
)
-- rollback DROP TABLE "BeanOrigin"

-- changeset lJordaan:create-table-beancolour
CREATE TABLE "BeanColour"
(
  "ColourId" serial PRIMARY KEY,
  "Colour" varchar(20) UNIQUE,
  "Description" varchar(500)
)
-- rollback DROP TABLE "BeanColour"

-- changeset lJordaan:create-table-basicbeaninformation
CREATE TABLE "BasicBeanInformation"
(
  "BeanId" serial PRIMARY KEY,
  "BeanName" varchar(50) UNIQUE,
  "ScientificName" varchar(50),
  "BeanContent" varchar(65535),
  "OriginId" integer NOT NULL,
  "TypeId" integer NOT NULL,
  "ShapeId" integer NOT NULL,
  "ColourId" integer NOT NULL
)
-- rollback DROP TABLE "BasicBeanInformation"

-- changeset lJordaan:alter-table-basicbeaninformation-origin-fk-creation
ALTER TABLE "BasicBeanInformation"
  ADD CONSTRAINT "BasicBeanInformation_OriginId_FK" FOREIGN KEY ("OriginId") REFERENCES "BeanOrigin"("OriginId");
-- rollback ALTER TABLE "BasicBeanInformation" DROP CONSTRAINT "BasicBeanInformation_OriginId_FK"

-- changeset lJordaan:alter-table-basicbeaninformation-type-fk-creation
ALTER TABLE "BasicBeanInformation"
  ADD CONSTRAINT "BasicBeanInformation_TypeId_FK" FOREIGN KEY ("TypeId") REFERENCES "BeanType"("TypeId");
-- rollback ALTER TABLE "BasicBeanInformation" DROP CONSTRAINT "BasicBeanInformation_TypeId_FK" 

-- changeset lJordaan:alter-table-basicbeaninformation-shape-fk-creation
ALTER TABLE "BasicBeanInformation"
  ADD CONSTRAINT "BasicBeanInformation_ShapeId_FK" FOREIGN KEY ("ShapeId") REFERENCES "BeanShape"("ShapeId");
-- rollback ALTER TABLE "BasicBeanInformation" DROP CONSTRAINT "BasicBeanInformation_ShapeId_FK"

-- changeset lJordaan:alter-table-basicbeaninformation-colour-fk-creation
ALTER TABLE "BasicBeanInformation"
  ADD CONSTRAINT "BasicBeanInformation_ColourId_FK" FOREIGN KEY ("ColourId") REFERENCES "BeanColour"("ColourId");
-- rollback ALTER TABLE "BasicBeanInformation" DROP CONSTRAINT "BasicBeanInformation_ColourId_FK"
