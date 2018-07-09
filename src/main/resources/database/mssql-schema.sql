
drop table matrix;
CREATE TABLE matrix
(
	id int  primary key  identity,
	name varchar(255) NOT NULL,
	remarks varchar(255),
	cost float ,
	style varchar(255) ,
	feature varchar(255) ,
	weight float,
	length float ,
	width float ,
	height float  ,
	img_url varchar(255) ,
	sell_type int,
	material int,
	color int,
	technology int,
	packing_type int,
	develop_cycle varchar(255),
	purchase_cycle varchar(255),
	produce_cycle varchar(255),
	logistics_cycle varchar(255),	
	 material_cost varchar(255),
	purchase_cost varchar(255),
	regular_cost varchar(255),
	logistics_cost varchar(255),
	fbaHead_cost varchar(255),
	fbaStore_cost varchar(255),
	fbaDelivery_cost varchar(255),
	fbaOverstock_cost varchar(255),
	declare_ename varchar(255),
	declare_name varchar(255),
	declare_value varchar(255),
	declare_weight varchar(255),
	declare_code varchar(255),
	declare_type varchar(255),
	category_first int,
	category_second int,
	update_date datetime not null default getdate(),
	create_date datetime not null default getdate(),
	update_by varchar(255),
	create_by varchar(255),
	del_flag int
) ;

drop table matrix_tag;
CREATE TABLE matrix_tag
(
	matrix_id int  NOT NULL,
	tag_id int NOT NULL primary key (matrix_id,tag_id),
) ;