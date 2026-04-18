package com.tanvantran.specification;

import org.jspecify.annotations.Nullable;
import org.springframework.data.jpa.domain.Specification;

import com.tanvantran.entity.Account;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class AccountSpecification implements Specification<Account>{
	
	private String field;
	private String operator;
	private Object value;
	
	public AccountSpecification(String field, String operator, Object value) {
		super();
		this.field = field;
		this.operator = operator;
		this.value = value;
	}

	@Override
	public @Nullable Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query,
			CriteriaBuilder criteriaBuilder) {
		// Tìm kiếm theo LIKE
		if (operator.equalsIgnoreCase("LIKE")) {
			if (field.equalsIgnoreCase("department")) {
				return criteriaBuilder.like(root.get(field).get("name"), "%" + value.toString() + "%");
			}else {
				return criteriaBuilder.like(root.get(field), "%" + value.toString() + "%");
			}
		}
		
		// Lọc dữ liệu Fillter id : 2-5
		if (operator.equalsIgnoreCase("BETWEEN")) {
//			value[2,5]
			Short[] values = (Short[]) value;
			short idFrom = values[0];
			short idTo = values[1];
			return criteriaBuilder.between(root.get(field), idFrom, idTo);
			
		}
		
		return null;
	}

}
