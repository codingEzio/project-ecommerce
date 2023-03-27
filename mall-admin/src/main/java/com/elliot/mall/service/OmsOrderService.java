package com.elliot.mall.service;

import com.elliot.mall.dto.*;
import com.elliot.mall.model.OmsOrder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OmsOrderService {

	/**
	 * Returns a list of OmsOrder objects based on filter criteria and pagination.
	 *
	 * @param queryParam OmsOrderQueryParam object that contains filter criteria
	 * @param pageSize   number of orders to display per page
	 * @param pageNum    current page number
	 * @return list of OmsOrder objects based on filter criteria and pagination
	 */
	List<OmsOrder> list(OmsOrderQueryParam queryParam, Integer pageSize, Integer pageNum);

	/**
	 * Batch delivers orders.
	 *
	 * @param deliveryParamList list of OmsOrderDeliveryParam objects containing
	 *                          delivery information for each order
	 * @return number of orders delivered
	 */
	@Transactional
	int delivery(List<OmsOrderDeliveryParam> deliveryParamList);

	/**
	 * Batch closes orders.
	 *
	 * @param ids  list of ids of orders to be closed
	 * @param note note to be added to each order (optional)
	 * @return number of orders closed
	 */
	@Transactional
	int close(List<Long> ids, String note);

	/**
	 * Batch deletes orders.
	 *
	 * @param ids list of ids of orders to be deleted
	 * @return number of orders deleted
	 */
	int delete(List<Long> ids);

	/**
	 * Returns OmsOrderDetail object for specified order id.
	 *
	 * @param id id of order whose details should be retrieved
	 * @return OmsOrderDetail object for specified order id
	 */
	OmsOrderDetail detail(Long id);

	/**
	 * Updates receiver information for specified order.
	 *
	 * @param receiverInfoParam OmsReceiverInfoParam object containing updated
	 *                          receiver information
	 * @return number of orders whose receiver information was updated
	 */
	@Transactional
	int updateReceiverInfo(OmsReceiverInfoParam receiverInfoParam);

	/**
	 * Updates cost information for specified order.
	 *
	 * @param moneyInfoParam OmsMoneyInfoParam object containing updated cost
	 *                       information
	 * @return number of orders whose cost information was updated
	 */
	@Transactional
	int updateMoneyInfo(OmsMoneyInfoParam moneyInfoParam);

	/**
	 * Updates note and/or status for specified order.
	 *
	 * @param id     id of order to be updated
	 * @param note   new note to be added to order (optional)
	 * @param status new status to be set for order (optional)
	 * @return number of orders whose note/status was updated
	 */
	@Transactional
	int updateNote(Long id, String note, Integer status);
}
