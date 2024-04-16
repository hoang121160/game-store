CREATE DATABASE WEB_Ban_Nick_LQ;
GO

USE WEB_Ban_Nick_LQ;
GO

-- Bảng người dùng
CREATE TABLE [User] (
    UserId INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(50) NOT NULL,
    [Password] NVARCHAR(100) NOT NULL,
    Email NVARCHAR(100) NOT NULL,
    Address NVARCHAR(255),
    Phone NVARCHAR(20),
    Roles NVARCHAR(5) NOT NULL CHECK (Roles IN ('ADMIN', 'USER'))
);

-- Bảng danh mục sản phẩm
CREATE TABLE Category (
    CategoryId INT PRIMARY KEY IDENTITY,
    CategoryName NVARCHAR(50) NOT NULL,
    Description NVARCHAR(MAX)
);

-- Bảng sản phẩm
CREATE TABLE Product (
    ProductId INT PRIMARY KEY IDENTITY,
    ProductName NVARCHAR(100) NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    Description NVARCHAR(MAX),
    ImageUrl NVARCHAR(255),
    CategoryId INT FOREIGN KEY REFERENCES Category(CategoryId)
);

-- Bảng đơn hàng
CREATE TABLE [Order] (
    OrderId INT PRIMARY KEY IDENTITY,
    UserId INT FOREIGN KEY REFERENCES [User](UserId),
    OrderDate DATE,
    TotalAmount DECIMAL(10,2),
    OrderStatus NVARCHAR(20)
);

-- Bảng chi tiết đơn hàng
CREATE TABLE OrderDetail (
    OrderDetailId INT PRIMARY KEY IDENTITY,
    OrderId INT FOREIGN KEY REFERENCES [Order](OrderId),
    ProductId INT FOREIGN KEY REFERENCES Product(ProductId),
    Quantity INT,
    Price DECIMAL(10,2)
);

-- Bảng góp ý từ người dùng
CREATE TABLE Feedback (
    FeedbackId INT PRIMARY KEY IDENTITY,
    UserId INT FOREIGN KEY REFERENCES [User](UserId),
    Email NVARCHAR(100) NOT NULL,
    FeedbackContent NVARCHAR(MAX),
    FeedbackDate DATE
);

-- Thêm dữ liệu vào bảng User
INSERT INTO [User] (Username, Password, Email, Address, Phone, Roles) 
VALUES 
    ('admin', 'admin123', 'admin@example.com', '123 Admin Street', '123456789', 'ADMIN'),
    ('user1', 'user123', 'user1@example.com', '456 User Street', '987654321', 'USER'),
    ('user2', 'user456', 'user2@example.com', '789 User Avenue', '654987321', 'USER'),
    ('user3', 'user789', 'user3@example.com', '321 User Road', '456789321', 'USER'),
    ('user4', 'userabc', 'user4@example.com', '987 User Lane', '321654987', 'USER');

-- Thêm dữ liệu vào bảng Category
INSERT INTO Category (CategoryName, Description) 
VALUES 
    ('Electronics', 'Category for electronic products'),
    ('Clothing', 'Category for clothing products'),
    ('Books', 'Category for books'),
    ('Furniture', 'Category for furniture'),
    ('Toys', 'Category for toys');

-- Thêm dữ liệu vào bảng Product
INSERT INTO Product (ProductName, Price, Description, ImageUrl, CategoryId) 
VALUES 
    ('Smartphone', 1000.00, 'High-end smartphone', 'smartphone.jpg', 1),
    ('T-shirt', 20.00, 'Casual T-shirt', 'tshirt.jpg', 2),
    ('Book: Introduction to SQL', 35.00, 'Beginner guide to SQL programming', 'sqlbook.jpg', 3),
    ('Wooden Desk', 150.00, 'Sturdy wooden desk for home office', 'desk.jpg', 4),
    ('LEGO Set', 50.00, 'Building blocks toy set for children', 'lego.jpg', 5);

-- Thêm dữ liệu vào bảng Order
INSERT INTO [Order] (UserId, OrderDate, TotalAmount, OrderStatus) 
VALUES 
    (1, '2024-04-16', 1000.00, 'Pending'),
    (2, '2024-04-17', 20.00, 'Completed'),
    (3, '2024-04-18', 35.00, 'Pending'),
    (4, '2024-04-19', 150.00, 'Completed'),
    (5, '2024-04-20', 50.00, 'Pending');

-- Thêm dữ liệu vào bảng OrderDetail
INSERT INTO OrderDetail (OrderId, ProductId, Quantity, Price) 
VALUES 
    (1, 1, 1, 1000.00),
    (2, 2, 1, 20.00),
    (3, 3, 1, 35.00),
    (4, 4, 1, 150.00),
    (5, 5, 1, 50.00);

-- Thêm dữ liệu vào bảng Feedback
INSERT INTO Feedback (UserId, Email, FeedbackContent, FeedbackDate) 
VALUES 
    (1, 'admin@example.com', 'Great service!', '2024-04-16'),
    (2, 'user1@example.com', 'Good quality products', '2024-04-17'),
    (3, 'user2@example.com', 'Fast delivery', '2024-04-18'),
    (4, 'user3@example.com', 'Easy checkout process', '2024-04-19'),
    (5, 'user4@example.com', 'Responsive customer support', '2024-04-20');

	select * from [User]