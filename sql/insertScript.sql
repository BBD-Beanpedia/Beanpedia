-- liquibase formatted sql

-- changeset lJordaan:insert-into-table-beantype
INSERT INTO "BeanType"
  ("BeanType", "Description")
  VALUES
  ('Legumes','This is a broad category that includes all beans and peas that grow in pods. Legumes are known for their ability to fix nitrogen in the soil, making them an important part of sustainable agriculture.'),
  ('Pulses','Pulses are the edible seeds of plants in the legume family. This category specifically refers to the dried seed and includes lentils, chickpeas, and dried peas and beans. Pulses are a staple in diets around the world due to their high protein and fiber content.'),
  ('Fresh Beans','These are beans harvested while still young and tender, before the seed inside the pod has fully matured. Green beans and snap beans fall into this category.'),
  ('Dried Beans','These are mature beans that are dried to remove moisture so they can be stored for long periods. Kidney beans, black beans, and pinto beans are examples of dried beans.'),
  ('Shell Beans','Shell beans are beans that are grown to full maturity, harvested, and then removed from their pods before eating. Unlike dried beans, shell beans are often cooked and eaten fresh. Examples include fresh lima beans and edamame (young soybeans).'),
  ('Oilseeds','Some legumes are grown primarily for the extraction of oil from their seeds. Soybeans are the most notable example in this category, used to produce vegetable oil and various soy products.');
-- rollback DELETE FROM TABLE "BeanType";

-- changeset ljordaan:insert-into-table-beanshape
INSERT INTO "BeanShape"
  ("Shape", "Description")
  VALUES
  ('Kidney-Shaped','Characteristic of kidney beans, with a curved surface and resembling a kidney.'),
  ('Round','Spherical or nearly spherical, common in peas and some types of lentils.'),
  ('Oval','A more elongated round shape, typical of navy beans and pinto beans.'),
  ('Elongated','Longer than they are wide, seen in runner beans and some types of Italian beans.'),
  ('Flat','Long and narrow, common in string beans and green beans.'),
  ('Irregular','Non-uniform shapes, which can be seen in heirloom varieties or certain types of dried beans.'),
  ('Heart-Shaped','This is less common but can be seen in some bean varieties when viewed from certain angles.'),
  ('Crescent-Shaped','Similar to kidney beans but with a more pronounced curve.');
-- rollback DELETE FROM TABLE "BeanShape";
 
-- changeset ljordaan:insert-into-table-beanorigin
INSERT INTO "BeanOrigin"
  ("Origin")
  VALUES
  ('Africa'),
  ('Asia'),
  ('Caribbean'),
  ('Central America'),
  ('Europe'),
  ('North America'),
  ('Oceania'),
  ('South America');
-- rollback DELETE FROM TABLE "BeanOrigin";

-- changeset ljordaan:insert-into-table-beancolour
INSERT INTO "BeanColour"
  ("Colour", "Description")
  VALUES
  ('Green', 'Common in fresh beans like green beans (also known as string beans or snap beans).'),
  ('Yellow', 'Seen in varieties of wax beans, which are similar to green beans but yellow in color.'),
  ('Red', 'Found in kidney beans and adzuki beans, offering a deep, rich red hue.'),
  ('Black', 'Characteristic of black beans and black turtle beans, known for their dark, glossy appearance.'),
  ('White', 'Common in navy beans, cannellini beans, and Great Northern beans.'),
  ('Brown', 'Seen in pinto beans and some varieties of lentils.'),
  ('Purple', ' Some beans, like certain varieties of kidney beans and heirloom beans, have a purple color.'),
  ('Speckled', 'Many beans have a speckled pattern, combining colors such as red and white in cranberry beans, or black and white in Orca beans.'),
  ('Mottled', 'Similar to speckled, but often with a more blended appearance, found in beans like pinto beans and some heirloom varieties.'),
  ('Pink', 'Some beans, when cooked, turn a soft pink hue, like certain types of pinto beans.');
-- rollback DELETE FROM TABLE "BeanColour";

-- changeset ljordaan:insert-into-table-basicbeaninformation
INSERT INTO "BasicBeanInformation"
  ("BeanName", "ScientificName", "BeanContent", "OriginId", "TypeId", "ShapeId", "ColourId")
  VALUES
  ('Black Turtle Bean', 'Phaseolus vulgaris', 'The Black Turtle Bean is a staple in Latin American cuisine, treasured for its dense, meaty texture that makes it ideal for dishes like feijoada and black bean soup. It thrives in the warm climates of Central America, showcasing the rich agricultural heritage of the region.', 4, 4, 1, 4),
  ('Chickpea', 'Cicer arietinum', 'Chickpeas, with their nutty taste and versatile texture, are a cornerstone of Middle Eastern cuisine. They serve as the primary ingredient in dishes such as hummus and falafel, reflecting a rich history that dates back thousands of years in regions like Asia.', 2, 2, 2, 5),
  ('Green Bean', 'Phaseolus vulgaris', 'Green Beans, characterized by their vibrant color and crisp texture, are a favorite in gardens and kitchens around North America. These beans are harvested young, making them tender and suitable for a variety of cooking methods, from steaming to sauteing.', 6, 3, 5, 1),
  ('Pinto Bean', 'Phaseolus vulgaris', 'Pinto Beans are celebrated in Mexican cuisine for their creamy texture and ability to beautifully absorb flavors. When cooked, they transform from their mottled raw appearance to a pleasing pink hue, making them a versatile ingredient in dishes from chili to refried beans.', 4, 4, 3, 6),
  ('Lima Bean', 'Phaseolus lunatus', 'Originating from South America, Lima Beans are named after the Peruvian capital. They boast a buttery texture and a delicate flavor, enjoyed both in their fresh green state and as dried beans. Lima beans are a testament to the diverse culinary uses of legumes.', 8, 5, 4, 5),
  ('Soybean', 'Glycine max', 'Soybeans are a global powerhouse, originating from Asia. They are utilized in an astonishing array of products, from soy milk and tofu to animal feed and biofuel, underscoring their importance in both culinary and industrial sectors.', 2, 6, 2, 5),
  ('Red Kidney Bean', 'Phaseolus vulgaris', 'The Red Kidney Bean is a hearty bean known for its presence in the classic dish, chili con carne. Its robust nature and attractive red hue make it a favorite for dishes that require beans to hold their shape through prolonged cooking.', 4, 4, 7, 3),
  ('Lentil', 'Lens culinaris', 'Lentils are one of the oldest known pulse crops, offering a spectrum of colors from green to red. Their quick-cooking nature and nutritional profile make them a popular choice worldwide, especially for soups and stews.', 2, 2, 2, 6),
  ('Navy Bean', 'Phaseolus vulgaris', 'The Navy Bean, small and white, is a classic ingredient in navy bean soup. Its mild flavor and soft texture make it a versatile bean, ideal for pureeing or using as a base in dishes that emphasize other flavors.', 6, 4, 2, 5),
  ('Adzuki Bean', 'Vigna angularis', 'Adzuki Beans are a small, red bean cherished in East Asian cuisine for their sweet flavor and smooth texture. They are often used in desserts, showcasing their unique ability to bridge the gap between sweet and savory applications.', 2, 4, 2, 3),
  ('Mung Bean', 'Vigna radiata', 'Mung Beans, known for their bright green color, are a staple in Asian cuisine. They are prized for their versatility, used whole in dishes, sprouted for salads, or ground into flour for use in noodles and desserts.', 2, 2, 2, 1),
  ('Black-Eyed Pea', 'Vigna unguiculata', 'The Black-Eyed Pea is a symbol of good luck in the Southern United States, especially when eaten as part of a New Year�s meal. Originating from West Africa, it has become integral to the culinary landscape of the American South, valued for its versatility and nutritional content.', 7, 4, 2, 8),
  ('Cannellini Bean', 'Phaseolus vulgaris', 'Cannellini Beans, with their large, white, and kidney-shaped profile, are a favorite in Italian cuisine. Known for their mild flavor and fluffy texture, they are a go-to bean for salads, soups, and stews.', 5, 4, 2, 5),
  ('Borlotti Bean', 'Phaseolus vulgaris', 'Also known as Cranberry Beans, Borlotti Beans are cherished in Italian cooking for their creamy texture and sweet taste. They are stunning in their raw form with a red speckled appearance that fades upon cooking.', 5, 4, 1, 8),
  ('Fava Bean', 'Vicia faba', 'Fava Beans, with a history of cultivation dating back to ancient times, are enjoyed in their tender green form or as dried beans. They hold a special place in Mediterranean cuisine, often associated with spring and fresh, simple dishes.', 1, 1, 4, 7),
  ('Great Northern Bean', 'Phaseolus vulgaris', 'The Great Northern Bean is a medium-sized white bean with a mild flavor, making it a popular choice in American and European cuisines for dishes requiring a bean that blends well with others without overpowering them.', 6, 4, 2, 5),
  ('Kidney Bean', 'Phaseolus vulgaris', 'Kidney Beans are named for their shape that resembles a human kidney. They are versatile and widely used in cuisines around the world, from Indian rajma to Louisiana red beans and rice.', 3, 4, 1, 3),
  ('Pink Bean', 'Phaseolus vulgaris', 'Pink Beans are often used in Caribbean cuisine, where they are appreciated for their sweet flavor and smooth texture. Similar to pinto beans but with a unique pink hue, they are a key ingredient in many traditional dishes.', 3, 4, 3, 10),
  ('Purple Bean', 'Phaseolus vulgaris', 'Purple Beans add a splash of color to the garden and the plate. While they turn green when cooked, their raw beauty and nutritional value make them a cherished variety among heirloom gardeners and chefs alike.', 1, 4, 7, 7),
  ('Flageolet Bean', 'Phaseolus vulgaris', 'Flageolet Beans are small, tender, and light green, considered a delicacy in French cuisine. Often served with lamb, their subtle flavor complements a variety of dishes, embodying the elegance of French cooking.', 5, 4, 2, 1),
  ('Cranberry Bean', 'Phaseolus vulgaris', 'Cranberry Beans, also known as Borlotti, are known for their creamy texture and striking appearance. These beans are a staple in Italian and Portuguese cuisines, where they are used in everything from salads to stews.', 3, 4, 4, 8);
-- rollback DELETE FROM TABLE "BasicBeanInformation";