create sequence  IF NOT EXISTS hibernate_sequence START with 1 INCREMENT BY 1;

create TABLE producers (
  id UUID NOT NULL,
  name VARCHAR(255),
  CONSTRAINT pk_producers PRIMARY KEY (id)
);

create TABLE products (
  id UUID NOT NULL,
  name VARCHAR(255),
  price DECIMAL,
  producer_id UUID,
  CONSTRAINT pk_products PRIMARY KEY (id)
);

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

create TABLE users_roles (
  role_id UUID NOT NULL,
  user_id UUID NOT NULL,
  CONSTRAINT pk_users_roles PRIMARY KEY (role_id, user_id)
);

alter table products add CONSTRAINT FK_PRODUCTS_ON_PRODUCER FOREIGN KEY (producer_id) REFERENCES producers (id);

alter table users_roles add CONSTRAINT fk_userol_on_role FOREIGN KEY (role_id) REFERENCES roles (id);

alter table users_roles add CONSTRAINT fk_userol_on_user FOREIGN KEY (user_id) REFERENCES users (id);