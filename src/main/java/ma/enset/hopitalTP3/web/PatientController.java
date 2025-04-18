package ma.enset.hopitalTP3.web;

import lombok.AllArgsConstructor;
import ma.enset.hopitalTP3.entities.Patient;
import ma.enset.hopitalTP3.repository.PatientRepository;
import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;
    @GetMapping("/index")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword) {

        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("listPatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);

        return "patients";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id, @RequestParam int page, @RequestParam String keyword) {
        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&keyword=" + keyword;
    }
    @GetMapping("/formPatient")
    public String formPatient(Model model) {
        model.addAttribute("patient", new Patient());
        return "formPatient";
    }

    @PostMapping("/savePatient")
    public String savePatient( Patient patient, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formPatient";
        }
        patientRepository.save(patient);
        return "redirect:/index";
    }
}



