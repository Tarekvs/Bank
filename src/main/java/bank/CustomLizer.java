package bank;

import bank.exceptions.TransactionAttributeException;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomLizer implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {


    @Override
    public JsonElement serialize(Transaction transaction, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject instance= new JsonObject();

        if (type==Payment.class){
            instance.addProperty("incominginterest",((Payment)transaction).getIncominginterest());
            instance.addProperty("outgoinginterest" ,((Payment)transaction).getOutgoinginterest());
            instance.addProperty("date" ,transaction.getDate());
            instance.addProperty("amount" ,transaction.getAmount());
            instance.addProperty("description" ,transaction.getDescription());
        }

        else if (type==OutgoingTransfer.class){
            instance.addProperty("sender" ,((OutgoingTransfer)transaction).getSender());
            instance.addProperty("recipient" ,((OutgoingTransfer)transaction).getRecipient());
            instance.addProperty("date" ,transaction.getDate());
            instance.addProperty("amount" ,transaction.getAmount());
            instance.addProperty("description" ,transaction.getDescription());
        }
        else if (type==IncomingTransfer.class){
            instance.addProperty("sender" ,((IncomingTransfer)transaction).getSender());
            instance.addProperty("recipient" ,((IncomingTransfer)transaction).getRecipient());
            instance.addProperty("date" ,transaction.getDate());
            instance.addProperty("amount" , transaction.getAmount());
            instance.addProperty("description" , transaction.getDescription());
        }
        JsonObject obj=new JsonObject();

        obj.addProperty("CLASSNAME", transaction.getClass().getSimpleName());
        obj.add("INSTANCE", instance);
        return obj;
    }

    @Override
        public Transaction deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jo = jsonElement.getAsJsonObject();
            JsonElement instance = jo.get("INSTANCE");
            JsonObject io = instance.getAsJsonObject();
            if (jo.get("CLASSNAME").getAsString().contains("Payment")){
                try {
                    return new Payment(
                            io.get("date").getAsString(),
                            io.get("amount").getAsDouble(),
                            io.get("description").getAsString(),
                            io.get("incominginterest").getAsDouble(),
                            io.get("outgoinginterest").getAsDouble()
                    );
                } catch (TransactionAttributeException e) {
                    System.out.println(e.getMessage());
                }
            }
            else if (jo.get("CLASSNAME").getAsString().contains("OutgoingTransfer")){
                try {
                    return new OutgoingTransfer(
                            io.get("date").getAsString(),
                            io.get("amount").getAsDouble(),
                            io.get("description").getAsString(),
                            io.get("sender").getAsString(),
                            io.get("recipient").getAsString()
                    );
                } catch (TransactionAttributeException e) {
                    System.out.println(e.getMessage());

                }
            }
            else if (jo.get("CLASSNAME").getAsString().contains("IncomingTransfer")){
                try {
                    return new IncomingTransfer(
                            io.get("date").getAsString(),
                            io.get("amount").getAsDouble(),
                            io.get("description").getAsString(),
                            io.get("sender").getAsString(),
                            io.get("recipient").getAsString()
                    );
                } catch (TransactionAttributeException e) {
                    System.out.println(e.getMessage());
                }
            }
            return null;
        }
}
