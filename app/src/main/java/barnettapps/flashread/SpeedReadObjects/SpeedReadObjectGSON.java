package barnettapps.flashread.SpeedReadObjects;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SpeedReadObjectGSON{
    private Gson gson;

    public SpeedReadObjectGSON(){
        GsonBuilder gsonBilder = new GsonBuilder();
        gsonBilder.registerTypeAdapter(SpeedReadObject.class, new SpeedReadGsonAdapter());
        gson = gsonBilder.create();
    }

    // SpeedReadObject to JSON
    public String toGson(SpeedReadObject _objectIn){
        return gson.toJson( _objectIn );
    }

    // covert JSON string to SpeedReadObject
    public SpeedReadObject toObject(String _Object, Class outputClass){
        SpeedReadObject SR = (SpeedReadObject) gson.fromJson(_Object,outputClass);
        return SR;
    }

    // Adapter becase Abstract SpeedReadObject, override serialise and deserialise
    private class SpeedReadGsonAdapter  implements JsonSerializer<SpeedReadObject>, JsonDeserializer<SpeedReadObject> {

        @Override
        public JsonElement serialize(SpeedReadObject src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            result.add("type", new JsonPrimitive(src.getClass().getSimpleName()));
            result.add("properties", context.serialize(src, src.getClass()));

            return result;
        }

        @Override
        public SpeedReadObject deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String type = jsonObject.get("type").getAsString();
            JsonElement element = jsonObject.get("properties");

            // get package name and remove last this class name
            String[] s = this.getClass().getName().split("\\.");
            StringBuilder sb= new StringBuilder();
            for (int i=0;i<s.length-1;i++){
                sb=sb.append(s[i]).append(".");
            }

            // TODO Clean this up
            try {
                return context.deserialize(element, Class.forName(sb.toString() + type));
            } catch (ClassNotFoundException cnfe) {
                try {
                    return context.deserialize(element,Class.forName(sb.toString() + "Puncuation." + type));
                } catch (ClassNotFoundException e) {
                    Log.w("GSON Objecct", "UNknown Element Type when trying to find class type");
                    throw new JsonParseException("Unknown element type: " + type, cnfe);
                }

            }
        }
    }

}
