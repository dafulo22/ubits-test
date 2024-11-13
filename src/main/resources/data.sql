-- Inserting data into the Module table
INSERT INTO module (title, description)
VALUES ('Aprendizaje',
        'Ejecuta la estrategia de aprendizaje de la compañía e impulsa las competencias de las personas.');
INSERT INTO module (title, description)
VALUES ('Desempeño',
        'Mejora el desempeño de las personas e identifica las capacidades de talento de la compañía.');
INSERT INTO module (title, description)
VALUES ('Diagnóstico',
        'Evalúa características clave de las personas y moviliza su mejora y desarrollo en la compañía.');
INSERT INTO module (title, description)
VALUES ('Encuestas',
        'Escucha la voz de las personas y eleva su compromiso y bienestar con la compañía.');

-- Inserting data into the Submodule table
INSERT INTO submodule (title, subtitle, description, button, module_id)
VALUES ('Submódulo 1', 'Plan de formación',
        'Crea y asigna el plan de formación para toda la compañía a partir de competencias y/o de contenidos.',
        'Conoce Mas', 1),
       ('Submódulo 2', 'LMS', 'Carga tus cursos y personaliza tu universidad corporativa',
        'Conoce Mas', 1),
       ('Submódulo 3', 'Content',
        'Accede a miles de contenidos para desarrollar el talento de tu compañía', 'Conoce Mas', 1),
       ('Submódulo 4', 'APP',
        'Accede en cualquier momento y lugar desde tu celular al contenido de UBITS', 'Conoce Mas',
        1),

       ('Submódulo 5', 'Objetivos',
        'Asigna y mide los objetivos de los colaboradores de la empresa', 'Conoce Mas', 2),
       ('Submódulo 6', '360°‍',
        'Evalúa el desempeño, identifica fortalezas y áreas de mejora, promueva desarrollo',
        'Conoce Mas', 2),

       ('Submódulo 7', 'Assessments de competencias',
        'Evalúa las competencias asociadas al plan de formación.', 'Conoce Mas', 3),

       ('Submódulo 8', 'eNPS',
        'Crea de forma simple y fácil la encuesta de lealtad de tus empleados', 'Conoce Mas', 4),
       ('Submódulo 9', 'Clima y cultura',
        'Gestiona la confidencialidad para fomentar respuestas honestas y sinceras', 'Conoce Mas',
        4),
       ('Submódulo 10', 'Encuestas',
        'Crea encuestas personalizadas para conocer la opinión de tu organización.', 'Conoce Mas',
        4);

-- Insert data into the Partner table
INSERT INTO partner (name)
VALUES ('Stanford Online');
INSERT INTO partner (name)
VALUES ('MIT OpenCourseWare');
INSERT INTO partner (name)
VALUES ('Harvard');

-- Insert data into the Category table
INSERT INTO category (name)
VALUES ('Cursos');
INSERT INTO category (name)
VALUES ('Charlas');
INSERT INTO category (name)
VALUES ('Ideas de Libros');
INSERT INTO category (name)
VALUES ('Prácticas');
INSERT INTO category (name)
VALUES ('Bit');

-- Insert data into the Level table
INSERT INTO level (name)
VALUES ('Basico');
INSERT INTO level (name)
VALUES ('Intermedio');
INSERT INTO level (name)
VALUES ('Dificil');

-- Insert data into the Catalog table with references to the previous tables
INSERT INTO catalog (title, partner_id, category_id, duration, level_id, description)
VALUES ('Marketing and Product Management (ENG)', 1, 1, 86, 2,
        'Stanford Online, gestionado por SCPD, ofrecerá cursos y programas desarrollados por la facultad. El track de Marketing y Gestión de Producto brindará habilidades clave para ser un gran gestor de productos.');

INSERT INTO catalog (title, partner_id, category_id, duration, level_id, description)
VALUES ('Introduction to Computer Science', 2, 1, 120, 1,
        'Curso introductorio sobre ciencias de la computación ofrecido por MIT OpenCourseWare.');

INSERT INTO catalog (title, partner_id, category_id, duration, level_id, description)
VALUES ('Data Science for Everyone', 3, 1, 60, 2,
        'Curso introductorio sobre ciencia de datos ofrecido por HarvardX.');

INSERT INTO catalog (title, partner_id, category_id, duration, level_id, description)
VALUES ('Advanced Machine Learning', 2, 1, 90, 3,
        'Curso avanzado sobre técnicas de aprendizaje automático, ofrecido por MIT OpenCourseWare.');
