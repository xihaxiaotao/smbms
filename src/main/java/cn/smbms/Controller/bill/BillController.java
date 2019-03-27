package cn.smbms.Controller.bill;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/bill")
public class BillController {

	   @Autowired
	   private BillService billService;
	   @Autowired
	   private ProviderService providerService;
	   
	   //订单列表
	   @RequestMapping("/getBillList")
	   public  String  getBillList(String queryProductName,Integer queryProviderId,Integer queryIsPayment,
			                       Integer currentPageNo,Integer pageSize,
                                   Model model,HttpServletRequest request){   
		     PageSupport  param=new PageSupport();
		   	 if(currentPageNo==null){
		   		 currentPageNo=1;
		   	 }
		   	 if(pageSize==null){
		   		 pageSize=5;
		   	 }
		   	 if(queryProductName==null){
		   		queryProductName=null;
		   	 }
		   	 if(queryProviderId==null){
		   		queryProviderId=null;
		   	 }
		   	 if(queryIsPayment==null){
		   		queryIsPayment=null;
			 }
		     List<Bill> billList=billService.getBillList(queryProductName, queryProviderId,queryIsPayment,(currentPageNo-1)*pageSize, pageSize);
		     List<Provider> providerList= providerService.getAllProviderName();
		     param.setPageSize(5);
		   	 param.setCurrentPageNo(currentPageNo);
		   	 param.setTotalCount(billService.getBillCount(queryProductName, queryProviderId,queryIsPayment));
		   	 
		   	 model.addAttribute("billList",billList);
		     model.addAttribute("providerList",providerList);
		   	 request.setAttribute("pageUrl","getBillList" );   	 //分页跳转的路径
		     request.setAttribute("queryProductName", queryProductName);
		 	 request.setAttribute("queryProviderId", queryProviderId);
			 request.setAttribute("queryIsPayment", queryIsPayment);
		   	 request.setAttribute("totalPageCount", param.getTotalPageCount());
		   	 request.setAttribute("totalCount", param.getTotalCount());
		   	 request.setAttribute("currentPageNo", param.getCurrentPageNo());
		   	 return "bill/billlist";
	   }
	   
	   //查看订单
	   @RequestMapping("/getBillInfo")
	   public String getBillInfo(String id,Model model){
		   Bill bill=billService.getBillById(id);
		   model.addAttribute("bill",bill);
		   return "bill/billview";
	   }
	   
	   //获取所有供应商
	   @ResponseBody
	   @RequestMapping("/getAllProvider")
	   public  Object  getAllProvider(){
		   List<Provider> providerList=providerService.getAllProviderName();
		   return  providerList;
	   }
	   
	   //页面跳转到"新增订单"页面
	   @RequestMapping("/toAddBill")
	   public  String  toAddBill(){
		   return "bill/billadd";
	   }
	   
	   //页面跳转到"修改订单"页面
	   @RequestMapping("/toUpdateBill")
	   public  String  toUpdateBill(String id,Model model){
		   Bill bill=billService.getBillById(id);
		   model.addAttribute("bill", bill);
		   return "bill/billmodify";
	   }
	   
	   //新增订单
	   @RequestMapping("/addBill")
	   public String addBill(Bill bill){
		   bill.setCreationDate(new Date());
		   int num=billService.add(bill);
		   if(num>0){
			   return  "redirect:/bill/getBillList";
		   }else{
			   return  "bill/billadd";
		   }
		   
	   }
	   
	   //修改订单
	   @RequestMapping("/updateBill")
	   public String  updateBill(Bill bill){
		   int num=billService.modify(bill);
		   if(num>0){
			   return  "redirect:/bill/getBillList";
		   }else{
			   return  "bill/billmodify";
		   }
	   }
	   
	   //删除订单
	   @ResponseBody
	   @RequestMapping("/delBill")
	   public Object delBill(String id){
		   HashMap<String,String> resultMap=new HashMap<String,String>();
		   int num=billService.deleteBillById(id, null);
		   if(num>0){
			   resultMap.put("delResult", "true");
		   }else{
			   resultMap.put("delResult", "false");
		   }
		   return  resultMap;
	   }
	   
}
