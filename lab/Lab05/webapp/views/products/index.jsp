<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Danh sách sản phẩm</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f8f8f8">
<c:if test="${empty sessionScope.name}">
	<c:redirect url="/views/login.jsp"/>
</c:if>
<div class="container-fluid p-5">
	<div class="row mb-5">
		<div class="col-md-6">
			<h3>Product Management</h3>
		</div>
		<div class="col-md-6 text-right">
			Xin chào <span class="text-danger">${sessionScope.name}</span> | <a
			href="${pageContext.servletContext.contextPath}/user/logout">Logout</a>
		</div>
	</div>
	<div class="row rounded border p-3">
		<div class="col-md-4">
			<h4 class="text-success" id="product-form-title">Thêm sản phẩm mới</h4>
			<form class="mt-3" method="post" action="/products/add" id="product-form">
				<input type="hidden" name="id" id="product-id">
				<div class="form-group">
					<label for="product-name">Tên sản phẩm</label>
					<input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="product-name"
						   name="name">
				</div>
				<div class="form-group">
					<label for="price">Giá sản phẩm</label>
					<input class="form-control" type="number" placeholder="Nhập giá bán" id="price" name="price">
				</div>
				<div class="form-group">
					<button class="btn btn-success mr-2" id="submit-btn">Thêm sản phẩm</button>
				</div>

				<div class="alert alert-danger d-none" id="error-msg">
				</div>
			</form>
		</div>
		<div class="col-md-8">
			<h4 class="text-success">Danh sách sản phẩm</h4>
			<p>Chọn một sản phẩm cụ thể để xem chi tiết</p>
			<table class="table table-striped">
				<thead>
				<tr>
					<th>STT</th>
					<th>Tên sản phẩm</th>
					<th>Giá</th>
					<th>Thao tác</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${requestScope.productList}" var="product">
					<tr>
						<td>${product.id}</td>
						<td><a href="#">${product.name}</a></td>
						<td>$${product.price}</td>
						<td>
							<button class="edit-btn btn btn-primary" data-id="${product.id}"
									onclick="changeToEditForm(this)">Chỉnh sửa
							</button>
							|
							<button data-toggle="modal" data-target="#confirmDeleteModal"
									onclick="showConfirmDelete(this)"
									data-id="${product.id}" class="delete-btn btn btn-danger">Xóa
							</button>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	 aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Are you sure you want to delete?</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" id="confirm-delete-btn">Delete</button>
				<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
</body>
<script>
	// Add the following code if you want the name of the file appear on select
	$(".custom-file-input").on("change", function () {
		var fileName = $(this).val().split("\\").pop();
		$(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	});

	let productForm = document.getElementById("product-form");
	let submitBtn = document.getElementById("submit-btn");

	function showConfirmDelete(btn) {
		let id = $(btn).data("id");
		$("#confirm-delete-btn").data("id", id)
	}

	$("#confirm-delete-btn").click((event) => {
		let id = $("#confirm-delete-btn").data("id");
		let destination = "/products/delete?id=" + id;
		window.location.href = destination;
	})

	function isFormInvalid() {
		let inputName = document.getElementById("product-name");
		let inputPrice = document.getElementById("price");
		let name = inputName.value;
		let price = inputPrice.value;
		let errorMsg = document.getElementById("error-msg");
		let isError = false;
		if (name.length === 0) {
			isError = true
			errorMsg.innerHTML = "Vui lòng nhập tên sản phẩm"
		} else if (price <= 0) {
			isError = true
			errorMsg.innerHTML = "Vui lòng nhập giá hợp lệ"
		} else {
			isError = false
			errorMsg.innerHTML = ""
		}

		if (isError) {
			errorMsg.classList.remove("d-none")
			errorMsg.classList.add("d-block")
		} else {
			errorMsg.classList.remove("d-block")
			errorMsg.classList.add("d-none")
		}

		return isError
	}

	function changeToEditForm(btn) {
		let id = $(btn).data("id");
		productForm.setAttribute("method", "get")
		$('#product-form-title').html("Cập nhật sản phẩm")
		$('#submit-btn').html("Cập nhật sản phẩm");
		productForm.setAttribute("action", "/products/edit")
		let tbody = document.getElementsByTagName("tbody")[0];
		let trs = [...tbody.children]
		let productName = ""
		trs.forEach(tr => {
			let tds = tr.children
			if (parseInt(tds[0].innerHTML) === parseInt(id)) {
				productName = tds[1].children[0].innerHTML
				price = tds[2].innerHTML.slice(1)
				$("#product-id").val(id)
				$("#product-name").val(productName)
				$("#price").val(price)
			}
		})
	}

	function changeToAddForm() {
		$('#product-form-title').html("Thêm sản phẩm mới")
		$('#submit-btn').html("Thêm sản phẩm");
		productForm.setAttribute("action", "/products/add")
	}


	submitBtn.addEventListener("click", (event) => {
		event.preventDefault()
		isFormInvalid() ? void (0) : productForm.submit()
	})
</script>
</body>
</html>
