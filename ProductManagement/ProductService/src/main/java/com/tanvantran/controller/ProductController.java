package com.tanvantran.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanvantran.dto.ProductDto;
import com.tanvantran.entity.Product;
import com.tanvantran.form.ProductFormForCreating;
import com.tanvantran.form.ProductFormForUpdating;
import com.tanvantran.service.IProductService;

@RestController
@RequestMapping(value = "api/v1/products")
@CrossOrigin("*")
public class ProductController {
	@Autowired
	private IProductService productService;

	// Lấy All danh sách sản phẩm
	@GetMapping()
	public ResponseEntity<?> getAllProducts() {
		List<Product> productListDB = productService.getAllProducts();
		List<ProductDto> productListDto = new ArrayList<>();

		// convert productListDB --> productListDto
		for (Product productDB : productListDB) {
			ProductDto productDto = new ProductDto();
			productDto.setId(productDB.getId());
			productDto.setName(productDB.getName());
			productDto.setPrice(productDB.getPrice());
			productDto.setInfo(productDB.getInfo());
			productDto.setDetail(productDB.getDetail());
			productDto.setRatingStar(productDB.getRatingStar());
			productDto.setImageName(productDB.getImageName());
			productDto.setManufacturerName(productDB.getManufacturer().getName().toString());
			productDto.setCategoryName(productDB.getCategory().getName());

			productListDto.add(productDto);
		}

		return new ResponseEntity<>(productListDto, HttpStatus.OK);
	}

// Tìm sản phẩm theo id
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProductByID(@PathVariable(name = "id") short id) {
		try {
			Product productDB = productService.getProductById(id);

			ProductDto productDto = new ProductDto();
			productDto.setId(productDB.getId());
			productDto.setName(productDB.getName());
			productDto.setPrice(productDB.getPrice());
			productDto.setInfo(productDB.getInfo());
			productDto.setDetail(productDB.getDetail());
			productDto.setRatingStar(productDB.getRatingStar());
			productDto.setImageName(productDB.getImageName());
			productDto.setManufacturerName(productDB.getManufacturer().getName().toString());
			productDto.setCategoryName(productDB.getCategory().getName());

			return new ResponseEntity<>(productDto, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}

	}

//	Thêm mới sản phẩm
	@PostMapping()
	public ResponseEntity<?> createNewproduct(@RequestBody ProductFormForCreating productNewForm) {
		try {
//			Thêm mới Product
//			Sau khi thêm mới, trả về thông tin Product vừa thêm
			Product productNew = productService.createNewProduct(productNewForm);

//			Convert
			ProductDto productNewDto = new ProductDto();
			productNewDto.setId(productNew.getId());
			productNewDto.setName(productNew.getName());
			productNewDto.setPrice(productNew.getPrice());
			productNewDto.setInfo(productNew.getInfo());
			productNewDto.setDetail(productNew.getDetail());
			productNewDto.setRatingStar(productNew.getRatingStar());
			productNewDto.setImageName(productNew.getImageName());
			productNewDto.setManufacturerName(productNew.getManufacturer().getName().toString());
			productNewDto.setCategoryName(productNew.getCategory().getName());

			return new ResponseEntity<>(productNewDto, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Not OK", HttpStatus.BAD_REQUEST);
		}

	}

//	Update sản phẩm
	@PutMapping(value = "/{id}")
	public ResponseEntity<?> updateProduct(@PathVariable(name = "id") short id,
			@RequestBody ProductFormForUpdating productUpdateForm) {
		try {
//			Thực hiện Update Product
//			Sau khi Update, trả về thông tin Product vừa Update
			Product productUpdate = productService.updateProduct(id, productUpdateForm);

//			Convert
			ProductDto productUpdateDto = new ProductDto();
			productUpdateDto.setId(productUpdate.getId());
			productUpdateDto.setName(productUpdate.getName());
			productUpdateDto.setPrice(productUpdate.getPrice());
			productUpdateDto.setInfo(productUpdate.getInfo());
			productUpdateDto.setDetail(productUpdate.getDetail());
			productUpdateDto.setRatingStar(productUpdate.getRatingStar());
			productUpdateDto.setImageName(productUpdate.getImageName());
			productUpdateDto.setManufacturerName(productUpdate.getManufacturer().getName().toString());
			productUpdateDto.setCategoryName(productUpdate.getCategory().getName());

			return new ResponseEntity<>(productUpdateDto, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}

	}

//	Xóa sản phẩm theo id
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable(name = "id") short id) {
		try {
//			Thực hiện lấy thông tin chi tiết của Product cần xóa, sau khi xóa xong thực hiện trả về thông tin chi tiết
//			của Product vừa xóa
			Product productDelete = productService.getProductById(id);

//			Convert
			ProductDto productDeleteDto = new ProductDto();
			productDeleteDto.setId(productDelete.getId());
			productDeleteDto.setName(productDelete.getName());
			productDeleteDto.setPrice(productDelete.getPrice());
			productDeleteDto.setInfo(productDelete.getInfo());
			productDeleteDto.setDetail(productDelete.getDetail());
			productDeleteDto.setRatingStar(productDelete.getRatingStar());
			productDeleteDto.setImageName(productDelete.getImageName());
			productDeleteDto.setManufacturerName(productDelete.getManufacturer().getName().toString());
			productDeleteDto.setCategoryName(productDelete.getCategory().getName());

//			Xóa Product
			productService.deleteProductById(id);

			return new ResponseEntity<>(productDeleteDto, HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
		}

	}

}

