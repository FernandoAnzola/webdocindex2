
package JExcel;

import com.jniwrapper.win32.jexcel.*;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.File;



public class FindExcelString
{
    

    public static void main(String[] args) throws ExcelException
    {
        //String a buscar pedido
        String strValue ="";
        //Abrimos el archivo
        File xlsFile = new File ("C:\\pablo.xls");
        Application application = new Application();
        Workbook workbook;
        
        try {
            workbook = application.openWorkbook(xlsFile);
            
            //Obtenemos la lista de hojas de trabajo pertenecientes
            List worksheets = workbook.getWorksheets();

        for (int i = 0; i < worksheets.size(); i++)
        {
            //Seleccionamos TODAS las hojas de 1 en 1 (reccoriendo la lista)
            Worksheet worksheet = (Worksheet)worksheets.get(i);
            
            //Ahora seleccionamos el rango (TODO LA HOJA)
            Range range = worksheet.selectAll();
            
            //Especificacion de los atributos de busqueda
            Range.SearchAttributes searchAttributes = new Range.SearchAttributes();
            searchAttributes.setCaseSensetive(true);
            searchAttributes.setLookIn(Range.FindLookIn.VALUES);
            searchAttributes.setForwardDirection(true);
            
            //seleccion de la celda (de la hoja)
            Cell cell = range.find(strValue,searchAttributes);
            if (cell==null)
            {
                //No encontrado
            }
            else
            {
                //Encontrado
            }
            //Necesita mirar si esa celda tiene celda siguiente
            cell = range.findNext();
            if (cell == null)
            {
                //No encontrado
            }
            else
            {
                //Encontrado
            }

        
        }
        
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ExcelException ex) {
            ex.printStackTrace();
        }
       
                
        application.close();
    }
}

