package com.ecomm.genshop.service.Process;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.ecomm.genshop.model.Account;
import com.ecomm.genshop.model.Address;
import com.ecomm.genshop.model.Orders;
import com.ecomm.genshop.model.OrdersItem;
import com.ecomm.genshop.model.Payment;
import com.ecomm.genshop.model.Product;
import com.ecomm.genshop.service.ProductService;
import com.ecomm.genshop.utils.UtilsDate;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

@Service
public class InvoiceGenerator {

    @Autowired
    ProductService productService;

    private static final String invoiceDir = System.getProperty("user.dir") + "/src/main/resources/invoice/";

    public void generateInvoice(Orders order, Address address, Account account, Payment payment,
            List<OrdersItem> ordersItems) {
        File dir = new File(invoiceDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String outputFile = invoiceDir + order.getOrderId() + ".pdf";
        File file = new File(outputFile);
        if (file.exists()) {
            file.delete();
        }

        try (InputStream inputStream = new ClassPathResource("invoice/invoice_template.html").getInputStream();
                ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            buffer.flush();

            String htmlTemplate = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
            Map<String, String> dataMap = new HashMap<>();
            dataMap.put("{{orderId}}", Integer.toString(order.getOrderId()));
            dataMap.put("{{orderDate}}", UtilsDate.toString(order.getOrderDate(), "dd/MM/yyyy"));
            dataMap.put("{{streetName}}", address.getStreetName());
            dataMap.put("{{districtName}}", address.getDistrictName());
            dataMap.put("{{cityName}}", address.getCityName());
            dataMap.put("{{provinceName}}", address.getProvinceName());
            dataMap.put("{{postalCode}}", Integer.toString(address.getPostalCode()));
            dataMap.put("{{countryName}}", address.getCountryName());
            dataMap.put("{{phoneNumber}}", account.getPhoneNumber().toString());
            dataMap.put("{{email}}", account.getEmail());
            dataMap.put("{{fullName}}", account.getFirstName() + account.getLastName());
            dataMap.put("{{generatedDate}}", new Date().toString());
            dataMap.put("{{subTotal}}", String.valueOf(order.getTotal()));
            dataMap.put("{{paymentType}}", payment.getPaymentName());

            StringBuilder orderItemsRows = new StringBuilder();

            for (OrdersItem orderItem : ordersItems) {
                Product product = productService.findByProductId(orderItem.getProductId())
                        .orElseThrow(
                                () -> new RuntimeException("Product not found for ID: " + orderItem.getProductId()));
                String orderItemRow = "<tr>" +
                        "<td>" + product.getProductName() + "</td>" +
                        "<td>" + product.getProductDescription() + "</td>" +
                        "<td>" + orderItem.getQuantity() + "</td>" +
                        "<td>" + orderItem.getPrice() + "</td>" +
                        "<td>" + orderItem.getSubTotal() + "</td>" +
                        "</tr>";

                orderItemsRows.append(orderItemRow);
            }

            htmlTemplate = htmlTemplate.replace("{{orderItemsRows}}", orderItemsRows.toString());
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                htmlTemplate = htmlTemplate.replace(entry.getKey(), entry.getValue());
            }

            PdfWriter writer = new PdfWriter(outputFile);
            PdfDocument pdfDocument = new PdfDocument(writer);
            ConverterProperties converterProperties = new ConverterProperties();
            HtmlConverter.convertToPdf(htmlTemplate, pdfDocument, converterProperties);

            System.out.println("Invoice generated successfully!");
        } catch (IOException e) {
            System.err.println("Error while generating the invoice: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteInvoices() {
        File directory = new File(invoiceDir);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            for (File file : files) {
                if (file.isFile()) {
                    handleDelete(file);
                }
            }
        }
    }

    public void handleDelete(File file) {
        if (file.exists()) {
            String fileName = file.getName();
            file.delete();
            System.out.println(fileName + " deleted");
        } else {
            System.out.println("File not found");
        }
    }
}
