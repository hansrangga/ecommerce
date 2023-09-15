-- Tabel Role
CREATE TABLE role (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(7) NOT NULL,
    role_description VARCHAR(15) NOT NULL
);

-- Tabel Payment
CREATE TABLE payment (
    payment_id SERIAL PRIMARY KEY,
    payment_name VARCHAR(10) NOT NULL
);

-- Tabel Coupon
CREATE TABLE coupon (
    coupon_id SERIAL PRIMARY KEY,
    coupon_code VARCHAR(12) UNIQUE NOT NULL,
    discount float8 NOT NULL,
    coupon_stock int4 NOT NULL,
    expiration_date DATE NOT NULL
);

-- Tabel account
CREATE TABLE account (
    user_id SERIAL PRIMARY KEY,
    user_name VARCHAR(20) NOT NULL,
    password VARCHAR(20) NOT NULL,
	first_name VARCHAR(50) NOT NULL,
	last_name VARCHAR(50) NOT NULL,
	phone_number int8 NOT NULL,
    email VARCHAR(75) UNIQUE NOT NULL,
    registrationDate DATE NOT NULL,
    role_id INTEGER REFERENCES role(role_id)
);

-- Tabel address
CREATE TABLE address (
    address_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES account(user_id) ON DELETE CASCADE,
    street_name VARCHAR(50) NOT NULL,
	district_name VARCHAR(50) NOT NULL,
	city_name VARCHAR(50) NOT NULL,
	province_name VARCHAR(50) NOT NULL,
    postal_code int4 UNIQUE NOT NULL,
    is_deleted boolean NOT NULL
);

-- Tabel Product
CREATE TABLE product (
    product_id SERIAL PRIMARY KEY,
    product_name VARCHAR(50) NOT NULL,
    product_description VARCHAR(255) NOT NULL,
    price float8 NOT NULL,
    wholesale_price float8 NOT NULL,
    offer_price float8 NOT NULL,
    stock int4 NOT NULL,
    category VARCHAR(20) NOT NULL
);

-- Tabel Review
CREATE TABLE review (
    review_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES account(user_id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES product(product_id) ON DELETE CASCADE,
    rating int2 NOT NULL,
    comment VARCHAR(255) NOT NULL,
    datePosted timestamptz NOT NULL
);

-- Tabel Cart
CREATE TABLE cart (
    cart_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES account(user_id) ON DELETE CASCADE,
    payment_id INTEGER REFERENCES payment(payment_id) ON DELETE CASCADE,
    coupon_id INTEGER REFERENCES coupon(coupon_id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES product(product_id) ON DELETE CASCADE,
    quantity int4 NOT NULL
);

-- Tabel Order
CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES account(user_id) ON DELETE CASCADE,
    address_id INTEGER REFERENCES address(address_id) ON DELETE CASCADE,
    payment_id INTEGER REFERENCES payment(payment_id) ON DELETE CASCADE,
    product_id INTEGER REFERENCES product(product_id) ON DELETE CASCADE,
    quantity int4 NOT NULL,
    price float8 NOT NULL,
    total float8 NOT NULL,
    status VARCHAR(25) NOT NULL
);

-- Tabel Ticket
CREATE TABLE ticket (
    ticket_id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES account(user_id) ON DELETE CASCADE,
    description TEXT NOT NULL,
    status VARCHAR(255) NOT NULL,
    assign_support VARCHAR(255),
    date_opened DATE NOT NULL,
    date_closed DATE
);

-- Tabel AuditLog
CREATE TABLE AuditLog (
    logID SERIAL PRIMARY KEY,
    userID INTEGER REFERENCES account(user_id) ON DELETE SET NULL,
    activityType VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    details TEXT
);