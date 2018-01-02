package ma.ac.ensa.gnotes.rest;

import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.model.dto.EtudiantDTO;
import ma.ac.ensa.gnotes.model.mapper.EtudiantDtoTOEntityMapper;
import ma.ac.ensa.gnotes.service.EtudiantModuleService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import ma.ac.ensa.gnotes.service.ModuleService;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class AdminController {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EtudiantService etudiantService;

    @Autowired
    private EtudiantModuleService etudiantModuleService;

    @RequestMapping(value = "importFile",method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public String importFile(@RequestPart("uploadFile") MultipartFile file) throws IOException {
        String message = "Import OK : Toutes les notes sont importees";

        logger.info("Start Import (fileName : " + file.getOriginalFilename() + ")");

        InputStream ExcelFileToRead = file.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row;
        HSSFCell cell;
        Iterator rows = sheet.rowIterator();

        int n_cells = 1;
        int n_rows = 1;

        Map<Integer,Module> modules = new HashMap<>();

        String annee = "";

        while (rows.hasNext()) {
            row = (HSSFRow) rows.next();
            Iterator cells = row.cellIterator();

            n_cells = 1;

            int name_position=2;

            Etudiant etudiant = null;

            double mark = 0;

            int mark_position = 5;

            int comment_position = 7;

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

                //save year
                if(n_rows==4 && n_cells==13){
                    annee = cell.getStringCellValue();
                }
                //modules names
                else if(n_rows == 14 && n_cells == name_position){
                    Module module = moduleService.findByNumero(cell.getStringCellValue().replace(" ","").split("-")[0]);
                    //System.out.println(module);
                    if(module != null) {
                        modules.put(n_cells+5,module); //to sync with student's first module comment
                    }
                    //next one
                    name_position += 3;
                }

                //students
                else if(n_rows >=18){
                    if(n_cells == 1) {
                        etudiant = etudiantService.findByNumero(Double.toString(cell.getNumericCellValue()));
                        //System.out.println(etudiant);
                    }

                    //save mark
                    else if(n_cells == mark_position){
                        //PcR Abs
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING){
                            if(cell.getStringCellValue().equals("PcR") || cell.getStringCellValue().equals("Abs")){
                                comment = cell.getStringCellValue();
                                isComment = true;
                            }else{
                                message = "Import KO : Il y a des notes non valides";
                            }

                        }
                        else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            mark = cell.getNumericCellValue();
                        }
                        mark_position += 3;
                    }

                    //save comment
                    else if(n_cells == comment_position){
                        if(!isComment){ //otherwise it comment will be empty
                            comment = cell.getStringCellValue();
                        }

                        comment_position +=3;

                        //add mark to BD
                        Module currentModule = modules.get(n_cells);
                        if(currentModule != null && etudiant != null && !comment.equals("")){
                            //check if record exist to update it or create a new one
                            EtudiantModule etudiantModule = etudiantModuleService.findByEtudiantAndModuleAndAnnee(etudiant, currentModule, annee);
                            if(etudiantModule == null){
                                etudiantModule = new EtudiantModule();
                                etudiantModule.setEtudiant(etudiant);
                                etudiantModule.setModule(currentModule);
                            }
                            etudiantModule.setNote(mark);
                            etudiantModule.setCommentaire(comment);
                            etudiantModule.setAnnee(annee);
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
        return message;
    }

    @RequestMapping(value = "createStudent", method = RequestMethod.POST)
    public String createStudent(@RequestBody EtudiantDTO etudiant){
        EtudiantDtoTOEntityMapper mapper = new EtudiantDtoTOEntityMapper();
        Etudiant etudiant1 = mapper.getModelMapper().map(etudiant, Etudiant.class);
        //Mapping error
        if(etudiant1.getDateDeNaissance() == null){
            return "KO : Data invalide";
        }
        Etudiant searched = etudiantService.findByNumeroOrCneOrCin(etudiant1.getNumero(), etudiant1.getCne(), etudiant1.getCin());
        if(searched == null){
            etudiantService.save(etudiant1);
            return "OK : ajouté avec succès";
        }else{
            return "KO : compte déjà existant";
        }
    }

    @RequestMapping(value = "fetchStudents/{numero}", method = RequestMethod.GET)
    public List<Etudiant> fetchStudents(@PathVariable("numero") String numero){
        if(numero.equals("nothing")){
            return etudiantService.findAll();
        }else{
            return etudiantService.findByNumeroContains(numero);
        }
    }
}
