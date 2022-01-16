create TABLE producers (
  id UUID NOT NULL,
   name VARCHAR(255),
   CONSTRAINT pk_producers PRIMARY KEY (id)
);
CREATE INDEX idx_producers_name ON producers (name ASC NULLS LAST);

create TABLE products (
  id UUID NOT NULL,
   name VARCHAR(255),
   price DECIMAL,
   producer_id UUID,
   CONSTRAINT pk_products PRIMARY KEY (id)
);
CREATE INDEX idx_products_name ON products (name ASC NULLS LAST);

create TABLE roles (
  id UUID NOT NULL,
   name VARCHAR(255) NOT NULL,
   CONSTRAINT pk_roles PRIMARY KEY (id)
);

create TABLE users (
  id UUID NOT NULL,
   email VARCHAR(255) NOT NULL,
   first_name VARCHAR(255) NOT NULL,
   second_name VARCHAR(255),
   password VARCHAR(255),
   CONSTRAINT pk_users PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_users_email ON users (email ASC NULLS LAST);

create TABLE users_roles (
  role_id UUID NOT NULL,
   user_id UUID NOT NULL,
   CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

alter table products add CONSTRAINT FK_PRODUCTS_ON_PRODUCER FOREIGN KEY (producer_id) REFERENCES producers (id);

alter table users_roles add CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

alter table users_roles add CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);