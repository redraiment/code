create table users (
  id serial primary key,
  name text,
  password text,
  email text,
  homepage text,
  key text,
  created_at timestamp,
  updated_at timestamp
);

create table issues (
  id serial primary key,
  user_id integer,
  parent_id integer,
  name text,
  description text,
  sort integer,
  created_at timestamp,
  updated_at timestamp
);

create table tools (
  id serial primary key,
  user_id integer,
  parent_id integer,
  name text,
  sort integer,
  created_at timestamp,
  updated_at timestamp
);

create table lexers (
  id serial primary key,
  name text,
  module text,
  class text,
  created_at timestamp,
  updated_at timestamp
);

create table solutions (
  id serial primary key,
  issue_id integer,
  created_at timestamp,
  updated_at timestamp
);

create table tags (
  id serial primary key,
  solution_id integer,
  tool_id integer,
  created_at timestamp,
  updated_at timestamp
);

create table snippets (
  id serial primary key,
  user_id integer,
  solution_id integer,
  lexer_id integer,
  code text,
  comment text,
  created_at timestamp,
  updated_at timestamp
);
