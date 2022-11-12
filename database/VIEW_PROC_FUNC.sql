

USE DSMT
GO

IF EXISTS (SELECT name FROM sys.procedures WHERE name = 'PROC_DIS_PRODUCT')
	DROP PROC PROC_DIS_PRODUCT
GO
CREATE PROCEDURE PROC_DIS_PRODUCT
	@top int
AS BEGIN 
	SELECT TOP (ISNULL(@top, 1000)) p.*, SUM(o.quantity) as 'quantity'
	FROM PRODUCTS p INNER JOIN ORDER_DETAILS o
		ON p.id = o.product_id 
	GROUP BY id, cosPrice, proPrice, name, descript, category_id, account_id
	ORDER BY SUM(o.quantity) DESC
END
GO
EXEC PROC_DIS_PRODUCT 10



IF EXISTS (SELECT name FROM sys.procedures WHERE name = 'PROC_TOP_PRODUCT')
	DROP PROC PROC_TOP_PRODUCT
GO
CREATE PROCEDURE PROC_TOP_PRODUCT
	@top int
AS BEGIN 
	SELECT TOP(ISNULL(@top, 1000)) p.*, COUNT(*) as 'quantity' FROM ORDER_DETAILS d
		INNER JOIN ORDERS o ON o.id = d.order_id
		INNER JOIN PRODUCTS p ON p.id = d.product_id
	GROUP BY p.id, cosPrice, proPrice, name, descript, category_id, p.account_id
	ORDER BY COUNT(*) DESC
END
GO
EXEC PROC_TOP_PRODUCT 10















