package br.com.buyFast;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.buyFast.dao.GenericDao;
import br.com.buyFast.dao.GenericDaoImp;
import br.com.buyFast.model.Address;
import br.com.buyFast.model.Administrator;
import br.com.buyFast.model.Bank;
import br.com.buyFast.model.Category;
import br.com.buyFast.model.Customer;
import br.com.buyFast.model.ItemsOrder;
import br.com.buyFast.model.Order;
import br.com.buyFast.model.OrderPK;
import br.com.buyFast.model.Product;
import br.com.buyFast.model.Settings;
import br.com.buyFast.model.StatusEnum;

/**
 * Classe de teste do banco de dados.
 */
public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GenericDao<Settings> dao = new GenericDaoImp<Settings>();
		GenericDao<Administrator> daoAdmin = new GenericDaoImp<Administrator>();
		GenericDao<Address> daoAddress = new GenericDaoImp<Address>();
		GenericDao<Order> daoOrder = new GenericDaoImp<Order>();
		GenericDao<Customer> daoCustomer = new GenericDaoImp<Customer>();
		GenericDao<Bank> daobank = new GenericDaoImp<Bank>();
		GenericDao<Category> daoCategory = new GenericDaoImp<Category>();
		GenericDao<Product> daoProduct = new GenericDaoImp<Product>();
		GenericDao<ItemsOrder> daoItemsOrder = new GenericDaoImp<ItemsOrder>();
		
		Category category = new Category();
		category.setName("Informática");
		daoCategory.save(category);
		
		Product product = new Product();
		product.setName("Carro");
		product.setPrice(12.2);
		product.setQuantityStock(100);
		product.setCategory(category);
		daoProduct.save(product);
		
		Administrator admin = new Administrator();
		admin.setName("daniel");
		admin.setCpf("32132");
		admin.setEmail("jhkjh");
		admin.setPassword("321321");
		admin.setSecondName("jkhiuy");
		admin.setUser("xxxxxx");
		
		Address address = new Address();
		address.setCity("nova iguaçu");
		daoAddress.save(address);
		
		daoAdmin.save(admin);
		
		dao.save(new Settings());
		
		List<Bank> listBank = new ArrayList<Bank>();
		
		listBank.add(new Bank("Bradesco"));
		listBank.add(new Bank("Itaú"));
		listBank.add(new Bank("Banco do Brasil"));
		
		for (Bank bank : listBank) {
			daobank.save(bank);
		}
		
		Order order = new Order();
		Customer customer = new Customer();
		
		customer.setName("daneil");
		customer.setCpf("09888990q3");
		customer.setEmail("dask@oi.com.br");
		customer.setPassword("sdsad");
		customer.setSecondName("Oliveira");
		customer.setAddress(address);
		
		daoCustomer.save(customer); 

		order.setBank(listBank.get(0));
		order.setOrderDate(new Timestamp(new Date().getTime()));
		order.setPaymentDate(new Timestamp(new Date().getTime()));
		order.setStatusEnum(StatusEnum.INPROCESS);
		order.setCustomer(customer);
		
		ItemsOrder itemsOrder = new ItemsOrder();
		itemsOrder.setProduct(product);
		itemsOrder.setPrice(123.45);
		itemsOrder.setQuantity(1);
		itemsOrder.setId(new OrderPK(1, 1));
		itemsOrder.setOrder(order);
		
		daoOrder.save(order);
		
		daoItemsOrder.save(itemsOrder);
		
		Customer newCustomer = daoCustomer.load(customer);
		
		for (Order order2 : newCustomer.getOrders()) {
			System.out.println(order2.getId());
			System.out.println(order2.getBank());
			System.out.println(order2.getCustomer());
			System.out.println(order2.getStatusEnum());
		}
	}

}
