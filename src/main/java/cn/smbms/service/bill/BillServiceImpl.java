package cn.smbms.service.bill;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillDao;
import cn.smbms.pojo.Bill;
@Service("BillService")
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;

	@Override
	public int add(Bill bill) {
		// TODO Auto-generated method stub
		return billDao.add(bill);
	}

	@Override
	public List<Bill> getBillList(String queryProductName,Integer queryProviderId,Integer queryIsPayment,Integer currentPageNo,Integer pageSize) {
		// TODO Auto-generated method stub
		return billDao.getBillList( queryProductName, queryProviderId, queryIsPayment, currentPageNo, pageSize);
	}

	@Override
	public int deleteBillById(String delId,String providerId) {
		// TODO Auto-generated method stub
		return billDao.deleteBillById(delId, providerId);
	}

	@Override
	public Bill getBillById(String id) {
		// TODO Auto-generated method stub
		return billDao.getBillById(id);
	}

	@Override
	public int modify(Bill bill) {
		// TODO Auto-generated method stub
		return billDao.modify(bill);
	}

	@Override
	public int getBillCount(String productName, Integer providerId,
			Integer isPayment) {
		// TODO Auto-generated method stub
		return billDao.getBillCount(productName, providerId, isPayment);
	}


}
