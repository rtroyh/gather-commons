package com.gather.gathercommons.json;

import com.gather.gathercommons.model.IDataTableModel;
import com.gather.gathercommons.model.IListModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hgajardofrancino
 */
public class JsonObjectFactory {
    public static JsonElement getJson(IDataTableModel model,
                                      Boolean includeHiddenCell) throws
                                                                 NoRowException {

        if (model == null) {
            return new JsonObject();
        }

        List<List<Object>> headers = model.getHeaders();
        List<List<Object>> titles = model.getTitles();
        List<List<Object>> rows = model.getRows();

        if (rows == null || rows.isEmpty()) {
            //throw new NoRowException("No existen regitros");
        }

        List<String> propertiesTitles = new ArrayList<>();
        propertiesTitles.add("");
        propertiesTitles.add("Mensaje_Error");
        propertiesTitles.add("Titulo");
        propertiesTitles.add("Subtitulo");

        JsonObject ouputObject = new JsonObject();
        ouputObject.add("titles",
                        getJson(titles,
                                propertiesTitles));

        JsonArray arrayObject = new JsonArray();

        for (List<Object> row : rows) {

            JsonObject jsonElement = new JsonObject();

            for (int indexHeader = 0; indexHeader < headers.size(); indexHeader++) {
                List<Object> header = headers.get(indexHeader);

                //skip hidden cell
                if (!includeHiddenCell && !(header.get(4).equals(1) || header.get(1).equals(5))) {
                    continue;
                }

                JsonPrimitive jsonPrimitive = getPrimitiveElement(row.get(indexHeader));
                jsonElement.add(header.get(0).toString(),
                                jsonPrimitive);
            }

            arrayObject.add(jsonElement);

        }

        ouputObject.add("data",
                        arrayObject);

        List<String> propertiesMetadata = new ArrayList<>();
        propertiesMetadata.add("Descripcion");
        propertiesMetadata.add("Tipo");
        propertiesMetadata.add("Decimales");
        propertiesMetadata.add("Visibilidad_0");
        propertiesMetadata.add("Visibilidad");
        //propertiesMetadata.add("Proporcion");

        ouputObject.add("metadata",
                        getJson(headers,
                                propertiesMetadata));

        return ouputObject;
    }

    public static JsonElement getJson(IDataTableModel model) throws
                                                             NoRowException {
        return getJson(model,
                       true);
    }

    public static JsonElement getJson(List<List<Object>> rows,
                                      List<String> propertyTitle) throws
                                                                  NoRowException {
        JsonArray array = new JsonArray();

        /*
        if (rows.isEmpty()) {
            throw new NoRowException("La lista de datos esta vacia");
        }*/

        if (propertyTitle == null) {

            for (List<Object> row : rows) {
                JsonObject jsonElement = new JsonObject();
                Integer indexElement = 0;
                for (Object element : row) {
                    JsonPrimitive jsonPrimitive;
                    if (element != null) {
                        jsonPrimitive = getPrimitiveElement(element);

                    } else {
                        jsonPrimitive = getPrimitiveElement("");
                    }

                    jsonElement.add(indexElement.toString(),
                                    jsonPrimitive);
                    indexElement++;
                }

                array.add(jsonElement);
            }
        } else {

            if (propertyTitle.isEmpty()) {
                throw new NoRowException("La lista de propiedades esta vacia");
            }


            for (List<Object> row : rows) {
                JsonObject jsonElement = new JsonObject();

                for (Integer indexCell = 0; indexCell < row.size(); indexCell++) {
                    Object cellElement = row.get(indexCell) != null ? row.get(indexCell) : "";
                    JsonPrimitive jsonPrimitive = getPrimitiveElement(cellElement);
                    try {
                        jsonElement.add(propertyTitle.get(indexCell),
                                        jsonPrimitive);
                    } catch (IndexOutOfBoundsException ex) {
                        jsonElement.add(indexCell.toString(),
                                        jsonPrimitive);
                    }
                }
                array.add(jsonElement);
            }

        }

        return array;
    }

    public static JsonElement getJson(List<List<Object>> rows) throws
                                                               NoRowException {
        return getJson(rows,
                       null);
    }

    public static JsonElement getJson(IListModel model) throws
                                                        NoRowException {
        return getJson(model.getRows(),
                       null);
    }

    public static JsonElement getJson(IListModel model,
                                      List<String> propertyTitle) throws
                                                                  NoRowException {
        return getJson(model.getRows(),
                       propertyTitle);
    }

    private static JsonPrimitive getPrimitiveElement(Object data) {
        JsonPrimitive jsonPrimitive;

        if (data instanceof Number) {
            jsonPrimitive = new JsonPrimitive((Number) data);
        } else if (data instanceof Boolean) {
            jsonPrimitive = new JsonPrimitive((Boolean) data);
        } else if (data instanceof Character) {
            jsonPrimitive = new JsonPrimitive((Character) data);
        } else {
            jsonPrimitive = new JsonPrimitive((String) data);
        }

        return jsonPrimitive;
    }
}
