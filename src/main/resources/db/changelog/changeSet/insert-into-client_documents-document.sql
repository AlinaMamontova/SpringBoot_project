--changeset Mamontova:1
insert into document (date_start, issue_organization,
                      issue_code, document_status, document_type_id) values
        ('2024-05-11', 'Долг по карте', 123, true, 1),
        ('2023-12-12', 'Пенсия', 111, true, 2),
        ('2023-11-21', 'Налоги', 345, true, 1);

insert into client_documents (client_id, document_id) values
        (2, 1),
        (1, 1),
        (3, 3);
