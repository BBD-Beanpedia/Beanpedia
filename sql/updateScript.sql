-- liquibase formatted sql

-- changeset lJordaan:alter-table-basicbeaninformation-beanname-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "BeanName" SET DATA TYPE varchar(50),
ALTER COLUMN "BeanName" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "BeanName" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-scientificname-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ScientificName" TYPE varchar(50),
ALTER COLUMN "ScientificName" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "ScientificName" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-beancontent-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "BeanContent" TYPE varchar(65535),
ALTER COLUMN "BeanContent" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "BeanContent" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-originid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "OriginId" TYPE integer,
ALTER COLUMN "OriginId" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "OriginId" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-typeid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "TypeId" TYPE integer,
ALTER COLUMN "TypeId" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "TypeId" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-shapeid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ShapeId" TYPE integer,
ALTER COLUMN "ShapeId" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "ShapeId" DROP NOT NULL;

-- changeset lJordaan:alter-table-basicbeaninformation-colourid-non-nullable
ALTER TABLE "BasicBeanInformation"
ALTER COLUMN "ColourId" TYPE integer,
ALTER COLUMN "ColourId" SET NOT NULL;
-- rollback ALTER TABLE "BasicBeanInformation" ALTER COLUMN "ColourId" DROP NOT NULL;

-- changeset lJordaan:alter-table-beantype-beantype-non-nullable
ALTER TABLE "BeanType"
ALTER COLUMN "BeanType" TYPE varchar(50),
ALTER COLUMN "BeanType" SET NOT NULL;
-- rollback ALTER TABLE "BeanType" ALTER COLUMN "BeanType" DROP NOT NULL;

-- changeset lJordaan:alter-table-beantype-description-non-nullable
ALTER TABLE "BeanType"
ALTER COLUMN "Description" TYPE varchar(500),
ALTER COLUMN "Description" SET NOT NULL;
-- rollback ALTER TABLE "BeanType" ALTER COLUMN "Description" DROP NOT NULL;

-- changeset lJordaan:alter-table-beanshape-shape-non-nullable
ALTER TABLE "BeanShape"
ALTER COLUMN "Shape" TYPE varchar(50),
ALTER COLUMN "Shape" SET NOT NULL;
-- rollback ALTER TABLE "BeanShape" ALTER COLUMN "Shape" DROP NOT NULL;

-- changeset lJordaan:alter-table-beanshape-description-non-nullable
ALTER TABLE "BeanShape"
ALTER COLUMN "Description" TYPE varchar(500),
ALTER COLUMN "Description" SET NOT NULL;
-- rollback ALTER TABLE "BeanShape" ALTER COLUMN "Description" DROP NOT NULL;

-- changeset lJordaan:alter-table-beanorigin-origin-non-nullable
ALTER TABLE "BeanOrigin"
ALTER COLUMN "Origin" TYPE varchar(20),
ALTER COLUMN "Origin" SET NOT NULL;
-- rollback ALTER TABLE "BeanOrigin" ALTER COLUMN "Origin" DROP NOT NULL;

-- changeset lJordaan:alter-table-beancolour-colour-non-nullable
ALTER TABLE "BeanColour"
ALTER COLUMN "Colour" TYPE varchar(20),
ALTER COLUMN "Colour" SET NOT NULL;
-- rollback ALTER TABLE "BeanColour" ALTER COLUMN "Colour" DROP NOT NULL;

-- changeset lJordaan:alter-table-beancolour-description-non-nullable
ALTER TABLE "BeanColour"
ALTER COLUMN "Description" TYPE varchar(500),
ALTER COLUMN "Description" SET NOT NULL;
-- rollback ALTER TABLE "BeanColour" ALTER COLUMN "Description" DROP NOT NULL;
