create table users (
  id integer primary key autoincrement,
  name text,
  password text,
  email text,
  homepage text,
  key text,
  created_at timestamp,
  updated_at timestamp
);

create table issues (
  id integer primary key autoincrement,
  user_id integer,
  parent_id integer,
  name text,
  description text,
  sort integer,
  created_at timestamp,
  updated_at timestamp
);

create table tools (
  id integer primary key autoincrement,
  user_id integer,
  parent_id integer,
  name text,
  sort integer,
  created_at timestamp,
  updated_at timestamp
);

create table lexers (
  id integer primary key autoincrement,
  name text,
  module text,
  class text,
  created_at timestamp,
  updated_at timestamp
);

create table solutions (
  id integer primary key autoincrement,
  issue_id integer,
  created_at timestamp,
  updated_at timestamp
);

create table tags (
  id integer primary key autoincrement,
  solution_id integer,
  tool_id integer,
  created_at timestamp,
  updated_at timestamp
);

create table snippets (
  id integer primary key autoincrement,
  user_id integer,
  solution_id integer,
  lexer_id integer,
  code text,
  comment text,
  created_at timestamp,
  updated_at timestamp
);
