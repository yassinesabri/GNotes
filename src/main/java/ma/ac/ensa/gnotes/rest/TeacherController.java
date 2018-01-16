package ma.ac.ensa.gnotes.rest;


import ma.ac.ensa.gnotes.model.Enseignant;
import ma.ac.ensa.gnotes.model.Etudiant;
import ma.ac.ensa.gnotes.model.EtudiantModule;
import ma.ac.ensa.gnotes.model.Module;
import ma.ac.ensa.gnotes.model.vo.ModuleVO;
import ma.ac.ensa.gnotes.model.vo.StudentModuleVO;
import ma.ac.ensa.gnotes.service.EnseignantService;
import ma.ac.ensa.gnotes.service.EtudiantModuleService;
import ma.ac.ensa.gnotes.service.EtudiantService;
import ma.ac.ensa.gnotes.service.ModuleService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/api")
public class TeacherController {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ModuleService moduleService;

    @Autowired
    private EnseignantService enseignantService;

    @Autowired
    private EtudiantModuleService etudiantModuleService;

    @Autowired
    private EtudiantService etudiantService;

    @RequestMapping(value = "fetchTeacherModules/{numero}", method = RequestMethod.GET)
    public List<ModuleVO> fetchTeacherModules(@PathVariable("numero") String numero){
        Enseignant enseignant=enseignantService.findByNumero(numero);
//        enseignant.setModules(null);
//        System.out.println(enseignant);
//        System.out.println(moduleService.findByEnseignant(enseignant));
        return moduleService.findByEnseignant(enseignant);
    }
    //
    @RequestMapping(value = "fetchStudentsModules/{numero}/{annee}", method = RequestMethod.GET)
    public List<StudentModuleVO> fetchStudentsModules(@PathVariable("numero") String numero,@PathVariable("annee") String annee){
        Module module=moduleService.findByNumero(numero);
        module.setEnseignant(null);
        annee=annee.replace('_','/');
        System.out.println(annee);
        List<EtudiantModule> listeEtudiantModule=etudiantModuleService.findByModuleAndAnnee(module,annee);
        System.out.println(listeEtudiantModule.size());
        List<StudentModuleVO> listStudentModuleVO=new ArrayList<StudentModuleVO>();
        for(int i=0;i<listeEtudiantModule.size();i++){
            StudentModuleVO stm=new StudentModuleVO();
            stm.setStudentFirstName(listeEtudiantModule.get(i).getEtudiant().getNom());
            stm.setStudentLastName(listeEtudiantModule.get(i).getEtudiant().getPrenom());
            stm.setStudentNumber(listeEtudiantModule.get(i).getEtudiant().getNumero());
            stm.setModuleName(listeEtudiantModule.get(i).getModule().getNom());
            stm.setCommentaire(listeEtudiantModule.get(i).getCommentaire());
            stm.setNote(listeEtudiantModule.get(i).getNote());
            listStudentModuleVO.add(stm);
        }
        System.out.println(listStudentModuleVO);
        return listStudentModuleVO;
    }

    @RequestMapping(value = "importTeacherFile",method = RequestMethod.POST, consumes = "multipart/form-data")
    @ResponseBody
    public String importFile(@RequestPart("uploadFile") MultipartFile file) throws IOException {
        String message = "Import OK : Toutes les notes sont importees";

//        logger.info("Start Import (fileName : " + file.getOriginalFilename() + ")");
//
        InputStream ExcelFileToRead = file.getInputStream();
        HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);
        HSSFSheet sheet = wb.getSheetAt(0);
//        HSSFRow rowss = sheet.getRow(18);
//        HSSFCell cellsss = rowss.getCell(0);
//        if (cellsss.getCellType() == HSSFCell.CELL_TYPE_STRING) {
//        System.out.println("Value from the Excel sheet :"+ cellsss.getStringCellValue());}
//        else{
//            System.out.println((long)cellsss.getNumericCellValue());
//        }
//        System.out.println("last cell nuuuuuuum "+rowss.getLastCellNum());
//        System.out.println("last  row nuuuuuuum "+sheet.getLastRowNum());
        HSSFRow row;
        HSSFCell cell;
        row = sheet.getRow(3);
        cell = row.getCell(12);
        String annee=cell.getStringCellValue();
//        System.out.println("Annee:   "+annee);
        String numeroModule="NII44104";
        System.out.println("numeroModule:   "+numeroModule);
        int j=4;
        row = sheet.getRow(13);
        for(;j<=row.getLastCellNum();j++){
            cell = row.getCell(j);
//            System.out.println(cell.getStringCellValue());
            if(cell.getStringCellValue().contains(numeroModule)){
                break;
            }
        }

        if(j==row.getLastCellNum()){
            return "Error modul not existing";
        }
//        System.out.println(j+" contains : "+cell.getStringCellValue());
        Module module=moduleService.findByNumero(numeroModule);
        for(int i=17;i<=18;i++){
            row = sheet.getRow(i);
            cell = row.getCell(0);
            long numberStudent=(long)cell.getNumericCellValue();
            Etudiant etudiant=etudiantService.findByNumero(numberStudent+"");
//            System.out.print(" student number "+numberStudent+" "+etudiant+" ");
            cell = row.getCell(j);
            if(cell.getCellType()== HSSFCell.CELL_TYPE_STRING){
                String studentCommentaire=cell.getStringCellValue();
//                System.out.println(" student note "+studentCommentaire+" ");
//
                EtudiantModule etudiantModule=etudiantModuleService.findByEtudiantAndModule(etudiant,module);
                if(etudiantModule==null){
                    etudiantModule =new EtudiantModule();
                }
                etudiantModule.setAnnee(annee);
                etudiantModule.setCommentaire(studentCommentaire);
                etudiantModule.setEtudiant(etudiant);
                etudiantModule.setNote(0);
                etudiantModule.setModule(module);
                etudiantModuleService.save(etudiantModule);
                continue;
            }
            double studentNote=cell.getNumericCellValue();
//            System.out.print(" student note "+studentNote+" ");
            cell = row.getCell(j+2);
            String studentCommentaire=cell.getStringCellValue();
//            System.out.println(" student commentaire "+studentCommentaire+" ");
            EtudiantModule etudiantModule=etudiantModuleService.findByEtudiantAndModule(etudiant,module);
            if(etudiantModule==null){
                etudiantModule =new EtudiantModule();
            }
            etudiantModule.setAnnee(annee);
            etudiantModule.setCommentaire(studentCommentaire);
            etudiantModule.setEtudiant(etudiant);
            etudiantModule.setNote(studentNote);
            etudiantModule.setModule(module);
            etudiantModuleService.save(etudiantModule);
        }



            return message;
    }
}
