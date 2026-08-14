import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Customer-Service", url = "http://localhost:9293")
public interface CustomerClinet {

    @GetMapping("/API/customer/v1/GetCustomerById/{id}")
    CustomerDetails getCustomerById(@PathVariable("id") Long id);

    GetMapping("/API/Customer/v1/GetCustomerDetailsById/{customer_id}/Addresses/{address_id}")
    AddressResponse getAddressByCustomerAndAddressId(@PathVariable("customer_id") Long customerId, @PathVariable("address_id") Long addressId);
}
