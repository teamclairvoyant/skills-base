
-- Designations Table Entries

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Associate Software Engineer" AS description,1 AS is_active,"Associate Software Engineer" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Associate Software Engineer") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Software Engineer" AS description,1 AS is_active,"Software Engineer" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Software Engineer") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Software Engineer II" AS description,1 AS is_active,"Software Engineer II" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Software Engineer II") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Sr. Engineer Software" AS description,1 AS is_active,"Sr. Engineer Software" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Sr. Engineer Software") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Lead Engineer – Software" AS description,1 AS is_active,"Lead Engineer – Software" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Lead Engineer – Software") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Manager - Engineering" AS description,1 AS is_active,"Manager - Engineering" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Manager - Engineering") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Sr. Manager -  Engineering" AS description,1 AS is_active,"Sr. Manager -  Engineering" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Sr. Manager -  Engineering") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Associate Software Quality Engineer" AS description,1 AS is_active,"Associate Software Quality Engineer" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Associate Software Quality Engineer") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Software Quality Engineer" AS description,1 AS is_active,"Software Quality Engineer" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Software Quality Engineer") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Software Quality Engineer II" AS description,1 AS is_active,"Software Quality Engineer II" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Software Quality Engineer II") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Sr. Engineer - Software Quality" AS description,1 AS is_active,"Sr. Engineer - Software Quality" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Sr. Engineer - Software Quality") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Lead Engineer - Software Quality" AS description,1 AS is_active,"Lead Engineer - Software Quality" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Lead Engineer - Software Quality") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Manager Software Quality" AS description,1 AS is_active,"Manager Software Quality" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Manager Software Quality") LIMIT 1;

INSERT INTO skillbasedb.designations (id,created_by,created_date,modified_by,modified_date,
            description,is_active,name)
            SELECT * FROM (SELECT uuid(),"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Sr. Manager - Software Quality" AS description,1 AS is_active,"Sr. Manager - Software Quality" AS name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.designations WHERE name="Sr. Manager - Software Quality") LIMIT 1;

--  Skills Table Entries

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Core Java" AS description,1 AS is_active,"Core Java" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Core Java") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Angular" AS description,1 AS is_active,"Angular" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Angular") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of MySQL" AS description,1 AS is_active,"MySQL" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="MySQL") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Spring Boot" AS description,1 AS is_active,"Spring Boot" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Spring Boot") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Microservices" AS description,1 AS is_active,"Microservices" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Microservices") LIMIT 1;


INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Docker" AS description,1 AS is_active,"Docker" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Docker") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of React JS" AS description,1 AS is_active,"React JS" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="React JS") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Node JS" AS description,1 AS is_active,"Node JS" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Node JS") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Python" AS description,1 AS is_active,"Python" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Python") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Django" AS description,1 AS is_active,"Django" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Django") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Express JS" AS description,1 AS is_active,"Express JS" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Express JS") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Artificial Intelligence" AS description,1 AS is_active,"Artificial Intelligence" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Artificial Intelligence") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Data Science" AS description,1 AS is_active,"Data Science" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Data Science") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Power BI" AS description,1 AS is_active,"Power BI" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Power BI") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of R Language" AS description,1 AS is_active,"R Language" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="R Language") LIMIT 1;

INSERT INTO skillbasedb.skills (id,created_by,created_date,modified_by,modified_date,
            description,is_active,skill_name)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Description of Scala" AS description,1 AS is_active,"Scala" AS skill_name) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills WHERE skill_name="Scala") LIMIT 1;


-- Category Table Entries

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Backend Developer" AS cat_name,"Description of Backend Developer" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="Backend Developer") LIMIT 1;

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "UI Developer" AS cat_name,"Description of UI Developer" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="UI Developer") LIMIT 1;

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Fullstack Developer" AS cat_name,"Description of Fullstack Developer" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="Fullstack Developer") LIMIT 1;

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Devops" AS cat_name,"Description of Devops" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="Devops") LIMIT 1;

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Cloud" AS cat_name,"Description of Cloud" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="Cloud") LIMIT 1;

INSERT INTO skillbasedb.category (id,created_by,created_date,modified_by,modified_date,
			cat_name,description,is_active)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
		    "Quality Analyst" AS cat_name,"Description of QA" AS description,1 AS is_active) as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.category WHERE cat_name="Quality Analyst") LIMIT 1;


-- Skill Rating Table Entries

INSERT INTO skillbasedb.skills_rating (id,created_by,created_date,description,is_active,
			name,modified_by,modified_date)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "Description of Very Low" AS description,1 AS is_active,"Very Low" AS name,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills_rating WHERE name="Very Low") LIMIT 1;

INSERT INTO skillbasedb.skills_rating (id,created_by,created_date,description,is_active,
			name,modified_by,modified_date)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "Description of Basic" AS description,1 AS is_active,"Basic" AS name,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills_rating WHERE name="Basic") LIMIT 1;


INSERT INTO skillbasedb.skills_rating (id,created_by,created_date,description,is_active,
			name,modified_by,modified_date)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "Description of Competent" AS description,1 AS is_active,"Competent" AS name,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills_rating WHERE name="Competent") LIMIT 1;

INSERT INTO skillbasedb.skills_rating (id,created_by,created_date,description,is_active,
			name,modified_by,modified_date)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "Description of Developed Skills" AS description,1 AS is_active,"Developed Skills" AS name,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills_rating WHERE name="Developed Skills") LIMIT 1;

INSERT INTO skillbasedb.skills_rating (id,created_by,created_date,description,is_active,
			name,modified_by,modified_date)
            SELECT * FROM (SELECT uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "Description of Highly Skilled" AS description,1 AS is_active,"Highly Skilled" AS name,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.skills_rating WHERE name="Highly Skilled") LIMIT 1;

-- Qualification Status Table Entries

INSERT INTO skillbasedb.qualification_status (id,created_by,created_date,modified_by,modified_date,
			description,is_active,name)
            SELECT * FROM (SELECT uuid() AS id ,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Nominated" AS description,1 AS is_active,"Nominated" AS name)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.qualification_status WHERE name="Nominated") LIMIT 1;

INSERT INTO skillbasedb.qualification_status (id,created_by,created_date,modified_by,modified_date,
			description,is_active,name)
            SELECT * FROM (SELECT uuid() AS id ,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Appearing" AS description,1 AS is_active,"Appearing" AS name)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.qualification_status WHERE name="Appearing") LIMIT 1;

INSERT INTO skillbasedb.qualification_status (id,created_by,created_date,modified_by,modified_date,
			description,is_active,name)
            SELECT * FROM (SELECT uuid() AS id ,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Completed" AS description,1 AS is_active,"Completed" AS name)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.qualification_status WHERE name="Completed") LIMIT 1;


-- Role Table Entries

INSERT INTO skillbasedb.role (id,created_by,created_date,modified_by,modified_date,
			description,role_name)
            SELECT * FROM (SELECT uuid() AS id ,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Role Admin" AS description,"ADMIN" AS role_name)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.role WHERE role_name="ADMIN") LIMIT 1;

INSERT INTO skillbasedb.role (id,created_by,created_date,modified_by,modified_date,
			description,role_name)
            SELECT * FROM (SELECT uuid() AS id ,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            "Description of Role User" AS description,"USER" AS role_name)  as tmp
            WHERE NOT EXISTS (SELECT id from skillbasedb.role WHERE role_name="USER") LIMIT 1;


-- User Table  -  ADMIN-(Default User)  - Password = "password"  - Stored in DB using BCrypt encoded

INSERT IGNORE INTO skillbasedb.user(id,created_by,created_date,modified_by,modified_date,
         email,grade,is_active,name,password)
         VALUES(uuid() ,"ADMIN","2023-01-23 18:27:12","ADMIN","2023-01-23 18:27:12",
         "admin@gmail.com","A",1,"ADMIN","$2a$12$Wfy/FMKBenCVwEBMHVVMOuby7oul0mrF3SgJUwtYBi8Rq/ajSj5e2");

-- User Designation Mapping

INSERT INTO skillbasedb.user_designation_mapping (id,created_by,created_date,modified_by,modified_date,
            is_active,designation_id,user_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1 AS is_active,
            (select id from skillbasedb.designations where name="Manager - Engineering") AS designation_id,
            (select id from skillbasedb.user where name="ADMIN") AS user_id) as tmp
            WHERE NOT EXISTS (SELECT designation_id FROM skillbasedb.user_designation_mapping
            WHERE designation_id=(select id from skillbasedb.designations where name="Manager - Engineering")) LIMIT 1;

-- User Role Mapping

INSERT INTO skillbasedb.user_role_mapping (id,created_by,created_date,modified_by,modified_date,
            role_id,user_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            (select id from skillbasedb.role where role_name="ADMIN") AS role_id,
            (select id from skillbasedb.user where name="ADMIN") AS user_id) as tmp
            WHERE NOT EXISTS (SELECT role_id,user_id FROM skillbasedb.user_role_mapping
            WHERE role_id=(select id from skillbasedb.role where role_name="ADMIN")) LIMIT 1;

-- User category Mapping

INSERT INTO skillbasedb.user_category_mapping (id,created_by,created_date,modified_by,modified_date,
            category_id,user_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            (select id from skillbasedb.category where cat_name="FullStack Developer") AS category_id,
            (select id from skillbasedb.user where name="ADMIN") AS user_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.user_category_mapping
            WHERE category_id=(select id from skillbasedb.category where cat_name="FullStack Developer")) LIMIT 1;


INSERT INTO skillbasedb.user_category_mapping (id,created_by,created_date,modified_by,modified_date,
            category_id,user_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,"ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,
            (select id from skillbasedb.category where cat_name="Cloud") AS category_id,
            (select id from skillbasedb.user where name="ADMIN") AS user_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.user_category_mapping
            WHERE category_id=(select id from skillbasedb.category where cat_name="Cloud")) LIMIT 1;

-- Skill Category Mapping details .

INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="Backend Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Core Java") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="Backend Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Core Java")) LIMIT 1;


INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="Backend Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Spring Boot") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="Backend Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Spring Boot")) LIMIT 1;

INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="Backend Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Microservices") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="Backend Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Microservices")) LIMIT 1;

INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="UI Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Angular") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="UI Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Angular")) LIMIT 1;

INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="UI Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="React JS") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="UI Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="React JS")) LIMIT 1;


INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="Backend Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Python") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="Backend Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Python")) LIMIT 1;


INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="FullStack Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Core Java") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="FullStack Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Core Java")) LIMIT 1;


INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="FullStack Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Angular") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="FullStack Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Angular")) LIMIT 1;

INSERT INTO skillbasedb.skill_category (id,created_by,created_date,modified_by,
            modified_date,is_active,category_id,skill_id)
            SELECT * FROM (select uuid() AS id,"ADMIN" AS created_by,"2023-01-23 18:27:12" AS created_date,
            "ADMIN" AS modified_by,"2023-01-23 18:27:12" AS modified_date,1,
            (select id from skillbasedb.category where cat_name="FullStack Developer") AS category_id,
            (select id from skillbasedb.skills where skill_name="Spring Boot") AS skill_id) as tmp
            WHERE NOT EXISTS (SELECT category_id FROM skillbasedb.skill_category
            WHERE category_id=(select id from skillbasedb.category where cat_name="FullStack Developer")
            AND skill_id =(select id from skillbasedb.skills where skill_name="Spring Boot")) LIMIT 1;