/*
 * 
 * 
 * 
 */
package supply.medium.home.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;
import java.text.*;
import java.util.*;
import supply.medium.home.bean.GlobalProductItemBean;
import supply.medium.home.bean.TermsConditionsBean;
import supply.medium.home.bean.TransactionRfqItemBean;
import supply.medium.home.bean.TransactionRfqBean;
import supply.medium.home.database.CountryMaster;
import supply.medium.home.database.GlobalProductItemMaster;
import supply.medium.home.database.QuantityTypeMaster;
import supply.medium.home.database.StateMaster;
import supply.medium.home.database.TermsConditionsMaster;
import supply.medium.home.database.TransactionRfqItemMaster;
import supply.medium.home.database.TransactionRfqMaster;
import supply.medium.home.mailing.AttachmentMailer;
import supply.medium.home.mailing.HTMLMailComposer;
import supply.medium.utility.ErrorMaster;
import supply.medium.utility.SmProperties;

public class GenerateRFQ {

    public static void generate(String path, String fileName, String rfqKey, String fromcompanyKey, String tocompanyKey) {
        try {
            String fromName = null;
            String fromCountry = null;
            String fromState = null;
            String fromCity = null;
            String fromAddress = null;
            String fromZipcode = null;
            String fromEmail = null;

            String toName = null;
            String toCountry = null;
            String toState = null;
            String toCity = null;
            String toAddress = null;
            String toZipcode = null;
            String toOutsider = null;
            String toEmail = null;
            String transId = null;
            String transDate = null;

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
            Date date = null;

            TransactionRfqBean tb = null;
            tb = TransactionRfqMaster.showByTransactionKeyFromToCompany(rfqKey, fromcompanyKey, "from");
            toOutsider = tb.getIsOutersideSupplier();

            fromName = tb.getBuyerCompanyName();
            fromCountry = tb.getBuyerCountry();
            fromState = tb.getBuyerState();
            fromCity = tb.getBuyerCity();
            fromEmail = tb.getBuyerEmail();
            fromAddress = tb.getBuyerAddress();
            fromZipcode = tb.getBuyerZipcode();

            transId = tb.getTransactionNo();
            transDate = tb.getCreatedOn();

            date = df.parse(transDate);

            if (toOutsider.equals("yes")) {
                String outsiderDetail[] = tb.getOutsiderAddress().split("@#@#@");
                if (outsiderDetail.length > 4) {
                    toName = outsiderDetail[0];
                    toCountry = outsiderDetail[1];
                    toState = outsiderDetail[2];
                    toCity = outsiderDetail[3];
                    toAddress = outsiderDetail[4];
                    toZipcode = outsiderDetail[5];
                    toEmail = outsiderDetail[6];
                }
            } else {
                tb = TransactionRfqMaster.showByTransactionKeyFromToCompany(rfqKey, tocompanyKey, "to");
                toName = tb.getBuyerCompanyName();
                toCountry = tb.getBuyerCountry();
                toState = tb.getBuyerState();
                toCity = tb.getBuyerCity();
                toAddress = tb.getBuyerAddress();
                toZipcode = tb.getBuyerZipcode();
                toEmail = tb.getBuyerEmail();

                toCountry = CountryMaster.showCountryFromKey(toCountry);
                toState = StateMaster.showStateFromKey(toState);
            }

            toCountry = CountryMaster.showCountryFromKey(toCountry);
            fromCountry = CountryMaster.showCountryFromKey(fromCountry);

            toState = StateMaster.showStateFromKey(toState);
            fromState = StateMaster.showStateFromKey(toState);

            Document document = new Document();

            SmProperties.folderPath = path;

            String filePath1 = SmProperties.folderPath + "company-" + tocompanyKey + File.separator+"transaction";
            File folder = new File(filePath1);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String fileLocation1 = filePath1 + File.separator + fileName + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileLocation1));

            String filePath2 = SmProperties.folderPath + "company-" + fromcompanyKey + File.separator+"transaction";
            File folder2 = new File(filePath2);
            if (!folder2.exists()) {
                folder2.mkdirs();
            }

            String fileLocation2 = filePath2 + File.separator + fileName + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(fileLocation2));

            document.open();
            Font fontHeading = new Font(Font.TIMES_ROMAN, 14, Font.BOLD);
            Font fontHeaderFooter = new Font(Font.TIMES_ROMAN, 12, Font.BOLD);
            Font fontDataBold = new Font(Font.TIMES_ROMAN, 10, Font.BOLD);
            Font fontDataNormal = new Font(Font.TIMES_ROMAN, 10, Font.NORMAL);
            Font fontFooter = new Font(Font.TIMES_ROMAN, 8, Font.NORMAL);

            Paragraph heading = new Paragraph("Request for Quote by " + fromName.toUpperCase() + "\n\n", fontHeading);
            heading.setAlignment(Element.ALIGN_CENTER);
            heading.setSpacingAfter(5);
            document.add(heading);

            PdfPTable table = new PdfPTable(2);
            PdfPCell cell = new PdfPCell(new Paragraph("To", fontHeaderFooter));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Name : " + toName + " \n"
                    + "Country : " + toCountry + "\n"
                    + "State/Province : " + toState + "\n"
                    + "City : " + toCity + "\n"
                    + "Address : " + toAddress + "\n"
                    + "Zip Code : " + toZipcode + "\n", fontDataNormal));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Trans Date\n" + df2.format(date) + "\n\n"
                    + "Trans Id\n" + transId + "\n", fontDataNormal));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(10.0f);
            table.addCell(cell);
            table.setSpacingAfter(25);
            document.add(table);

            table = new PdfPTable(5);
            cell = new PdfPCell(new Paragraph("S.No", fontDataBold));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(2.0f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Item Name", fontDataBold));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(2.0f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Item Description", fontDataBold));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(2.0f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Quantity", fontDataBold));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(2.0f);
            table.addCell(cell);
            cell = new PdfPCell(new Paragraph("Ship Date", fontDataBold));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(2.0f);
            table.addCell(cell);

            ArrayList itemList = TransactionRfqItemMaster.showRfqItemFromRfqKey(rfqKey);
            TransactionRfqItemBean trib = null;
            int serialNo = 1;
            GlobalProductItemBean pb = null;
            for (int i = 0; i < itemList.size(); i++) {
                trib = (TransactionRfqItemBean) itemList.get(i);

                date = df.parse(trib.getShipDate());
                pb = GlobalProductItemMaster.showByItemKey(trib.getItemKey());

                cell = new PdfPCell(new Paragraph(serialNo + "", fontDataNormal));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(pb.getItemName(), fontDataNormal));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(pb.getItemDescription(), fontDataNormal));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(trib.getQuantity() + " " + QuantityTypeMaster.showCodeByKey(trib.getQuantityUnitKey()), fontDataNormal));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(df2.format(date), fontDataNormal));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorderWidth((float) 1.0);
                cell.setPadding(2.0f);
                table.addCell(cell);

                serialNo++;
            }
            table.setSpacingAfter(25);
            document.add(table);

            table = new PdfPTable(2);
            cell = new PdfPCell(new Paragraph("From", fontHeaderFooter));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(10.0f);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Name : " + fromName + "\n"
                    + "Country : " + fromCountry + "\n"
                    + "State/Province : " + fromState + "\n"
                    + "City : " + fromCity + "\n"
                    + "Address : " + fromAddress + "\n"
                    + "Zip Code : " + fromZipcode + "\n", fontDataNormal));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorderWidth((float) 1.0);
            cell.setPadding(10.0f);
            table.addCell(cell);
            table.setSpacingAfter(50);
            document.add(table);

            TermsConditionsBean tcb = TermsConditionsMaster.show(fromcompanyKey, "RFQ");
            if (tcb != null) {
                Paragraph Terms_conditions = new Paragraph("Terms & Conditions", fontDataBold);
                Terms_conditions.setAlignment(Element.ALIGN_LEFT);
                Terms_conditions.setSpacingAfter(5);
                document.add(Terms_conditions);

                tcb = new TermsConditionsBean();
                tcb.setTextMessage("Not any Terms and Conditions...");

                Paragraph Terms_conditions_data = new Paragraph(tcb.getTextMessage(), fontDataNormal);
                Terms_conditions_data.setAlignment(Element.ALIGN_LEFT);
                document.add(Terms_conditions_data);
            }

            String footerMessage = SmProperties.pdfFooterMessage;
            Paragraph footer = new Paragraph(footerMessage, fontFooter);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.add(footer);

            document.close();
            HTMLMailComposer mcomp = new HTMLMailComposer();
            String message = mcomp.composeRFQ(fromName + " (" + fromEmail + ") has requested you for quote.");
            AttachmentMailer am = new AttachmentMailer();
            String to[] = {toEmail};
            am.composeAndSendMail(to, fromName + " has requested you for quote via SupplyMedium", message, filePath1, fileName + ".pdf");
        } catch (Exception ex) {
            ErrorMaster.insert("Exception at generate in GenerateRFQ : " + ex.getMessage());
        }

    }

}
