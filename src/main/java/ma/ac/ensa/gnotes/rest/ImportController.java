package ma.ac.ensa.gnotes.rest;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.service.EtudiantModuleService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import ma.ac.ensa.gnotes.service.ModuleService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class ImportController {

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantModuleService etudiantModuleService;

    @RequestMapping(value = "importFile",method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public String importFile(@RequestPart("uploadFile") MultipartFile file) throws IOException {
        System.out.println("Start Import (fileName : " + file.getOriginalFilename() + ")");

        InputStream ExcelFileToRead = file.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        Iterator rows = sheet.rowIterator();

        int n_cells = 1;
        int n_rows = 1;
        Map<Integer,Module> modules = new HashMap<>();

        while (rows.hasNext()) {
            row = (HSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            n_cells = 1;
            int name_position=2;
            Etudiant etudiant = null;

            int mark_position = 5;
            int comment_position = 7;
            double mark = 0;
            String comment = "";
            boolean isComment = false;
            
            while (cells.hasNext()) {
                cell = (HSSFCell) cells.next();
                /*System.out.print("["+n_rows+":"+n_cells+"] :");
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    System.out.println(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.println(cell.getNumericCellValue() + " ");
                } else {
                    System.out.println();
                }*/

                //modules names
                if(n_rows == 14 && n_cells == name_position){
                    Module module = moduleService.findByNumero(cell.getStringCellValue().replace(" ","").split("-")[0]);
                    //System.out.println(module);
                    if(module != null) {
                        modules.put(n_cells+5,module); //to sync with student's first module comment
                    }
                    //next one
                    name_position += 3;
                }

                //students
                if(n_rows >=18){
                    if(n_cells == 1) {
                        etudiant = etudiantService.findByNumero((long)cell.getNumericCellValue());
                        System.out.println(etudiant);
                    }

                    //save mark
                    if(n_cells == mark_position){
                        //PcR Abs
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            comment = cell.getStringCellValue();
                            isComment = true;
                        }
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            mark = cell.getNumericCellValue();
                        }
                        mark_position += 3;
                    }

                    //save comment
                    if(n_cells == comment_position){
                        if(!isComment){
                            comment = cell.getStringCellValue();
                        }
                        comment_position +=3;


                        //add mark to BD
                        Module currentModule = modules.get(n_cells);
                        if(currentModule != null && etudiant != null){
                            EtudiantModule etudiantModule = new EtudiantModule();
                            etudiantModule.setEtudiant(etudiant);
                            etudiantModule.setModule(currentModule);
                            etudiantModule.setNote(mark);
                            etudiantModule.setCommentaire(comment);
                            etudiantModuleService.save(etudiantModule);
                        }

                        //reset for next mark
                        isComment = false;
                        mark = 0;
                        comment = "";
                    }

                }

                n_cells++;
            }

            n_rows++;
        }
        return "IMPORT OK";
    }
}
