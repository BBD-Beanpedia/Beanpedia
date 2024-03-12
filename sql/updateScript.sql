-- liquibase formatted sql

-- changeset lJordaan:alter-table-basicbeaninformation-shape-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "BeanName" varchar(50) NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "BeanName" varchar(50)

-- changeset lJordaan:alter-table-basicbeaninformation-scientificname-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ScientificName" varchar(50) NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "ScientificName" varchar(50)

-- changeset lJordaan:alter-table-basicbeaninformation-beancontent-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "BeanContent" varchar(65535) NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "BeanContent" varchar(65535)

-- changeset lJordaan:alter-table-basicbeaninformation-originid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "OriginId" integer NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "OriginId" integer

-- changeset lJordaan:alter-table-basicbeaninformation-typeid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "TypeId" integer NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "TypeId" integer

-- changeset lJordaan:alter-table-basicbeaninformation-shapeid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ShapeId" integer NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "ShapeId" integer

-- changeset lJordaan:alter-table-basicbeaninformation-colourid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ColourId" integer NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN ColourId integer

-- changeset lJordaan:alter-table-beantype-beantype-non-nullable
ALTER TABLE "BeanType"
ALTER COLUMN "BeanType" varchar(50) NOT NULL;
-- rollback ALTER TABLE "BeanType" ALTER COLUMN "BeanType" varchar(50)

-- changeset lJordaan:alter-table-beantype-description-non-nullable
ALTER TABLE "BeanType"
ALTER COLUMN "Description" varchar(500) NOT NULL;
-- rollback ALTER TABLE "BeanType" ALTER COLUMN "Description" varchar(500)

-- changeset lJordaan:alter-table-beanshape-shape-non-nullable
ALTER TABLE "BeanShape"
ALTER COLUMN Shape varchar(50) NOT NULL;
-- rollback ALTER TABLE "BeanShape" ALTER COLUMN "Shape" varchar(50)

-- changeset lJordaan:alter-table-beanshape-description-non-nullable
ALTER TABLE "BeanShape"
ALTER COLUMN "Description" varchar(500) NOT NULL;
-- rollback ALTER TABLE "BeanShape" ALTER COLUMN "Description" varchar(500)

-- changeset lJordaan:alter-table-beanorigin-origin-non-nullable
ALTER TABLE "BeanOrigin"
ALTER COLUMN "Origin" varchar(20) NOT NULL;
-- rollback ALTER TABLE "BeanOrigin" ALTER COLUMN "Origin" varchar(20)

-- changeset lJordaan:alter-table-beancolour-colour-non-nullable
ALTER TABLE "BeanColour"
ALTER COLUMN "Colour" varchar(20) NOT NULL;
-- rollback ALTER TABLE "BeanColour" ALTER COLUMN "Colour" varchar(20)

-- changeset lJordaan:alter-table-beancolour-description-non-nullable
ALTER TABLE "BeanColour"
ALTER COLUMN "Description" varchar(500) NOT NULL;
-- rollback ALTER TABLE "BeanColour" ALTER COLUMN "Description" varchar(500)