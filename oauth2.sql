/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : PostgreSQL
 Source Server Version : 100001
 Source Host           : localhost:5432
 Source Catalog        : oauth2
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 100001
 File Encoding         : 65001

 Date: 06/02/2018 15:48:38
*/


-- ----------------------------
-- Table structure for oauth_access_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_access_token";
CREATE TABLE "public"."oauth_access_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "token" bytea DEFAULT NULL,
  "authentication_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "user_name" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "client_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "authentication" bytea DEFAULT NULL,
  "refresh_token" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_access_token"."token_id" IS 'MD5加密的access_token的值';
COMMENT ON COLUMN "public"."oauth_access_token"."token" IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN "public"."oauth_access_token"."authentication_id" IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN "public"."oauth_access_token"."user_name" IS '登录的用户名';
COMMENT ON COLUMN "public"."oauth_access_token"."client_id" IS '客户端ID';
COMMENT ON COLUMN "public"."oauth_access_token"."authentication" IS 'OAuth2Authentication.java对象序列化后的二进制数据';
COMMENT ON COLUMN "public"."oauth_access_token"."refresh_token" IS 'MD5加密果的refresh_token的值';

-- ----------------------------
-- Table structure for oauth_approvals
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_approvals";
CREATE TABLE "public"."oauth_approvals" (
  "userid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "clientid" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "scope" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "status" varchar(10) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "expiresat" timestamp(6) DEFAULT NULL,
  "lastmodifiedat" timestamp(6) DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_approvals"."userid" IS '登录的用户名';
COMMENT ON COLUMN "public"."oauth_approvals"."clientid" IS '客户端ID';
COMMENT ON COLUMN "public"."oauth_approvals"."scope" IS '申请的权限';
COMMENT ON COLUMN "public"."oauth_approvals"."status" IS '状态（Approve或Deny）';
COMMENT ON COLUMN "public"."oauth_approvals"."expiresat" IS '过期时间';
COMMENT ON COLUMN "public"."oauth_approvals"."lastmodifiedat" IS '最终修改时间';

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_details";
CREATE TABLE "public"."oauth_client_details" (
  "client_id" varchar(256) COLLATE "pg_catalog"."default" NOT NULL DEFAULT NULL,
  "resource_ids" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "client_secret" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "scope" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "authorized_grant_types" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "web_server_redirect_uri" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "authorities" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "access_token_validity" int4 DEFAULT NULL,
  "refresh_token_validity" int4 DEFAULT NULL,
  "additional_information" varchar(4096) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "autoapprove" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_client_details"."client_id" IS '客户端ID';
COMMENT ON COLUMN "public"."oauth_client_details"."resource_ids" IS '资源ID集合,多个资源时用逗号(,)分隔';
COMMENT ON COLUMN "public"."oauth_client_details"."client_secret" IS '客户端密匙';
COMMENT ON COLUMN "public"."oauth_client_details"."scope" IS '客户端申请的权限范围';
COMMENT ON COLUMN "public"."oauth_client_details"."authorized_grant_types" IS '客户端支持的grant_type';
COMMENT ON COLUMN "public"."oauth_client_details"."web_server_redirect_uri" IS '重定向URI';
COMMENT ON COLUMN "public"."oauth_client_details"."authorities" IS '客户端所拥有的Spring Security的权限值，多个用逗号(,)分隔';
COMMENT ON COLUMN "public"."oauth_client_details"."access_token_validity" IS '访问令牌有效时间值(单位:秒)';
COMMENT ON COLUMN "public"."oauth_client_details"."refresh_token_validity" IS '更新令牌有效时间值(单位:秒)';
COMMENT ON COLUMN "public"."oauth_client_details"."additional_information" IS '预留字段';
COMMENT ON COLUMN "public"."oauth_client_details"."autoapprove" IS '用户是否自动Approval操作';

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO "public"."oauth_client_details" VALUES ('client', NULL, '$2a$10$1N/.LvTJuYpvxDzoJ1KdvuPDdV/kDSQE9Cxm9BzB1PreyzK6gmFRe', 'read,write', 'authorization_code,client_credentials,password,refresh_token', 'http://localhost:8080', 'ROLE_USER', 1800, 86400, NULL, 'false');

-- ----------------------------
-- Table structure for oauth_client_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_client_token";
CREATE TABLE "public"."oauth_client_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "token" bytea DEFAULT NULL,
  "authentication_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "user_name" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "client_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_client_token"."token_id" IS 'MD5加密的access_token值';
COMMENT ON COLUMN "public"."oauth_client_token"."token" IS 'OAuth2AccessToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN "public"."oauth_client_token"."authentication_id" IS 'MD5加密过的username,client_id,scope';
COMMENT ON COLUMN "public"."oauth_client_token"."user_name" IS '登录的用户名';
COMMENT ON COLUMN "public"."oauth_client_token"."client_id" IS '客户端ID';

-- ----------------------------
-- Table structure for oauth_code
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_code";
CREATE TABLE "public"."oauth_code" (
  "code" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "authentication" bytea DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_code"."code" IS '授权码(未加密)';
COMMENT ON COLUMN "public"."oauth_code"."authentication" IS 'AuthorizationRequestHolder.java对象序列化后的二进制数据';

-- ----------------------------
-- Table structure for oauth_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS "public"."oauth_refresh_token";
CREATE TABLE "public"."oauth_refresh_token" (
  "token_id" varchar(256) COLLATE "pg_catalog"."default" DEFAULT NULL,
  "token" bytea DEFAULT NULL,
  "authentication" bytea DEFAULT NULL
)
;
COMMENT ON COLUMN "public"."oauth_refresh_token"."token_id" IS 'MD5加密过的refresh_token的值';
COMMENT ON COLUMN "public"."oauth_refresh_token"."token" IS 'OAuth2RefreshToken.java对象序列化后的二进制数据';
COMMENT ON COLUMN "public"."oauth_refresh_token"."authentication" IS 'OAuth2Authentication.java对象序列化后的二进制数据';

-- ----------------------------
-- Primary Key structure for table oauth_client_details
-- ----------------------------
ALTER TABLE "public"."oauth_client_details" ADD CONSTRAINT "oauth_client_details_pkey" PRIMARY KEY ("client_id");
