import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;


public class Client {
	public static void main(String[] args) throws JAXBException{
		Customer cust = new Customer();
		cust.setAge(30);
		cust.setId(1);
		cust.setName("John");
		
		String xml = objToXML(cust);
		System.out.println(xml);
		
		Customer customer = xmlToObj(xml);
		System.out.println(customer.toString());
	}
	
	/**
	 * convert Customer object to XML String
	 * @param customer
	 * @return XML String representation of Customer object
	 * @throws JAXBException
	 */
	public static String objToXML(Customer customer) throws JAXBException{
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Customer.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			StringWriter sw = new StringWriter();
			jaxbMarshaller.marshal(customer, sw);
			
			return sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new JAXBException("unable to marshall Customer to XML");
		}
	}
	
	/**
	 * convert an XML String to Customer object
	 * @param xml
	 * @return Customer
	 * @throws JAXBException
	 */
	public static Customer xmlToObj(String xml) throws JAXBException{
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			StreamSource ss = new StreamSource(new StringReader(xml));
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(ss);
			return customer;
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new JAXBException("unable to unmarshall XML to Customer");
		}
	}
}
