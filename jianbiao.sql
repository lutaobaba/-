/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/4 10:25:44                            */
/*==============================================================*/


drop table if exists Commodity_orders;

drop table if exists Commodity_purchase;

drop table if exists Fresh_kind;

drop table if exists Full_Fold_Association;

drop table if exists Full_Fold_Information;

drop table if exists Limited_time_promotional_information;

drop table if exists Product_Recipe_Recommendation;

drop table if exists Product_information;

drop table if exists Recipe_information;

drop table if exists User_Information;

drop table if exists admin_information;

drop table if exists coupon;

drop table if exists delivery_address;

drop table if exists product_review;

/*==============================================================*/
/* Table: Commodity_orders                                      */
/*==============================================================*/
create table Commodity_orders
(
   order_id             varchar(20) not null,
   product_id           varchar(20) not null,
   user_id              varchar(20),
   address_id           varchar(20),
   old_money            varchar(50) not null,
   new_money            varchar(50) not null,
   coupon_id            varchar(20) not null,
   request_time         timestamp not null,
   order_status         varchar(50) not null,
   number               double not null,
   price                varchar(50) not null,
   discount             varchar(50) not null,
   primary key (order_id)
);

/*==============================================================*/
/* Table: Commodity_purchase                                    */
/*==============================================================*/
create table Commodity_purchase
(
   purchase_id          varchar(20) not null,
   admin_id             varchar(20),
   Ingredients_id       varchar(20) not null,
   number               double not null,
   status               varchar(50) not null,
   primary key (purchase_id)
);

/*==============================================================*/
/* Table: Fresh_kind                                            */
/*==============================================================*/
create table Fresh_kind
(
   kind_id              varchar(20) not null,
   kind_name            varchar(50) not null,
   kind_des             varchar(255) not null,
   primary key (kind_id)
);

/*==============================================================*/
/* Table: Full_Fold_Association                                 */
/*==============================================================*/
create table Full_Fold_Association
(
   product_id           varchar(20) not null,
   fold_id              varchar(20) not null,
   start_date           timestamp not null,
   end_date             timestamp not null,
   primary key (product_id, fold_id)
);

/*==============================================================*/
/* Table: Full_Fold_Information                                 */
/*==============================================================*/
create table Full_Fold_Information
(
   fold_id              varchar(20) not null,
   content              varchar(255) not null,
   number               int not null,
   discount             varchar(50) not null,
   start_date           timestamp not null,
   end_date             timestamp not null,
   primary key (fold_id)
);

/*==============================================================*/
/* Table: Limited_time_promotional_information                  */
/*==============================================================*/
create table Limited_time_promotional_information
(
   promotion_id         varchar(20) not null,
   product_id           varchar(20),
   promotion_price      double not null,
   promotion_num        double not null,
   star_time            timestamp not null,
   end_time             timestamp not null,
   primary key (promotion_id)
);

/*==============================================================*/
/* Table: Product_Recipe_Recommendation                         */
/*==============================================================*/
create table Product_Recipe_Recommendation
(
   product_id           varchar(20) not null,
   recipe_id            varchar(20) not null,
   des                  varchar(255) not null,
   primary key (product_id, recipe_id)
);

/*==============================================================*/
/* Table: Product_information                                   */
/*==============================================================*/
create table Product_information
(
   product_id           varchar(20) not null,
   kind_id              varchar(20),
   product_name         varchar(50) not null,
   price                double not null,
   vip_price            double not null,
   number               double not null,
   norm                 varchar(50) not null,
   details              varchar(255) not null,
   primary key (product_id)
);

/*==============================================================*/
/* Table: Recipe_information                                    */
/*==============================================================*/
create table Recipe_information
(
   recipe_id            varchar(20) not null,
   recipe_name          varchar(50) not null,
   recipe_use           varchar(50) not null,
   step                 varchar(255) not null,
   image                longblob not null,
   primary key (recipe_id)
);

/*==============================================================*/
/* Table: User_Information                                      */
/*==============================================================*/
create table User_Information
(
   user_id              varchar(20) not null,
   user_name            varchar(20) not null,
   user_sex             varchar(5) not null,
   user_pwd             varchar(50) not null,
   phone                varchar(50) not null,
   email                varchar(50) not null,
   city                 varchar(50) not null,
   registration_time    timestamp not null,
   vip                  bool not null,
   vip_end              timestamp not null,
   primary key (user_id)
);

/*==============================================================*/
/* Table: admin_information                                     */
/*==============================================================*/
create table admin_information
(
   admin_id             varchar(20) not null,
   admin_name           varchar(20) not null,
   admin_pwd            varchar(50) not null,
   primary key (admin_id)
);

/*==============================================================*/
/* Table: coupon                                                */
/*==============================================================*/
create table coupon
(
   coupon_id            varchar(20) not null,
   order_id             varchar(20),
   content              varchar(255) not null,
   use_amount           double not null,
   relief_amount        double not null,
   start_date           timestamp not null,
   end_date             timestamp not null,
   primary key (coupon_id)
);

/*==============================================================*/
/* Table: delivery_address                                      */
/*==============================================================*/
create table delivery_address
(
   address_id           varchar(20) not null,
   user_id              varchar(20),
   province             varchar(50) not null,
   city                 varchar(50) not null,
   area                 varchar(50) not null,
   address              varchar(255) not null,
   user_name            varchar(50) not null,
   phone                varchar(50) not null,
   primary key (address_id)
);

/*==============================================================*/
/* Table: product_review                                        */
/*==============================================================*/
create table product_review
(
   user_id              varchar(20) not null,
   product_id           varchar(20) not null,
   content              varchar(255) not null,
   date                 timestamp not null,
   star                 int not null,
   primary key (user_id, product_id)
);

alter table Commodity_orders add constraint FK_have foreign key (address_id)
      references delivery_address (address_id) on delete restrict on update restrict;

alter table Commodity_orders add constraint FK_order foreign key (user_id)
      references User_Information (user_id) on delete restrict on update restrict;

alter table Commodity_orders add constraint FK_order_details foreign key (product_id)
      references Product_information (product_id) on delete restrict on update restrict;

alter table Commodity_purchase add constraint FK_buy foreign key (admin_id)
      references admin_information (admin_id) on delete restrict on update restrict;

alter table Full_Fold_Association add constraint FK_full_fold_information foreign key (fold_id)
      references Full_Fold_Information (fold_id) on delete restrict on update restrict;

alter table Full_Fold_Association add constraint FK_full_fold_product foreign key (product_id)
      references Product_information (product_id) on delete restrict on update restrict;

alter table Limited_time_promotional_information add constraint FK_promotional foreign key (product_id)
      references Product_information (product_id) on delete restrict on update restrict;

alter table Product_Recipe_Recommendation add constraint FK_product_recommendation foreign key (product_id)
      references Product_information (product_id) on delete restrict on update restrict;

alter table Product_Recipe_Recommendation add constraint FK_recipe_recommendation foreign key (recipe_id)
      references Recipe_information (recipe_id) on delete restrict on update restrict;

alter table Product_information add constraint FK_contain foreign key (kind_id)
      references Fresh_kind (kind_id) on delete restrict on update restrict;

alter table coupon add constraint FK_use foreign key (order_id)
      references Commodity_orders (order_id) on delete restrict on update restrict;

alter table delivery_address add constraint FK_set foreign key (user_id)
      references User_Information (user_id) on delete restrict on update restrict;

alter table product_review add constraint FK_evaluate foreign key (user_id)
      references User_Information (user_id) on delete restrict on update restrict;

alter table product_review add constraint FK_evaluation foreign key (product_id)
      references Product_information (product_id) on delete restrict on update restrict;

