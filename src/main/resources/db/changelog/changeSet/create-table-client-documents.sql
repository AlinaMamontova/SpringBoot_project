--changeSet Mamontova:1
create table client_documents
(
    client_id   int not null,
    document_id int not null,

    primary key (client_id, document_id),
    foreign key (client_id) references client (id),
    foreign key (document_id) references document (id)
);