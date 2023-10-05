package bank;

import bank.exceptions.TransactionAttributeException;
import com.google.gson.*;

import java.lang.reflect.Type;

public class CustomLizer implements JsonSerializer<Transaction>, JsonDeserializer<Transaction> {


     /**
     * Serializes a given {@code Transaction} object to a {@code JsonElement}.
     *
     * @param transaction               The transaction object to be serialized.
     * @param type                      The specific type of transaction.
     * @param jsonSerializationContext  The context for serialization.
     * @return                          A JsonElement representation of the transaction.
     */

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
    
    /**
     * Deserializes a {@code JsonElement} to a {@code Transaction} object.
     *
     * @param jsonElement               The JsonElement to be deserialized.
     * @param type                      The expected type of the deserialized object.
     * @param jsonDeserializationContext The context for deserialization.
     * @return                          A Transaction object, or null if deserialization fails.
     * @throws TransactionAttributeException      If there is an error during the deserialization process.
     */
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
