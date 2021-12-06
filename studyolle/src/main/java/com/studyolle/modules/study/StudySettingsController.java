package com.studyolle.modules.study;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyolle.modules.account.CurrentAccount;
import com.studyolle.modules.account.Account;
import com.studyolle.modules.tag.Tag;
import com.studyolle.modules.zone.Zone;
import com.studyolle.modules.study.form.StudyDescriptionForm;
import com.studyolle.modules.tag.TagForm;
import com.studyolle.modules.tag.TagRepository;
import com.studyolle.modules.tag.TagService;
import com.studyolle.modules.zone.ZoneForm;
import com.studyolle.modules.zone.ZoneRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/study/{path}/settings")
@RequiredArgsConstructor
public class StudySettingsController {

    private final StudyRepository studyRepository;
    private final StudyService studyService;
    private final ModelMapper modelMapper;
    private final TagService tagService;
    private final TagRepository tagRepository;
    private final ZoneRepository zoneRepository;
    private final ObjectMapper objectMapper;

    @GetMapping("/description")
    public String viewStudySetting(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdate(account, path); // 서비스 로직 처리 이후, 정보가 담긴 Study study 객체를 Study study에 담고
        // 모델에 값을 담는다~
        model.addAttribute(account);
        model.addAttribute(study);
        // 매퍼는 왼쪽에서 오른쪽으로
        // 즉 값이 담긴 study 객체를 StudyDescriptionForm.class에 매핑시킨다.
        model.addAttribute(modelMapper.map(study, StudyDescriptionForm.class));
        // 여기까지 모델에 담긴 정보 요약
        /**
         * model
         * data : account, study, shortDescription, fullDescription
         */
        return "study/settings/description";
    }

    @PostMapping("/description")
    public String updateStudyInfo(@CurrentAccount Account account, @PathVariable String path,
                                  @Valid StudyDescriptionForm studyDescriptionForm, Errors errors,
                                  Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdate(account, path);

        if (errors.hasErrors()) {
            model.addAttribute(account);
            model.addAttribute(study);
            return "study/settings/description";
        }
        // submit 되어 온 Post 값들이 이상이 없다면
        // 업데이트를 수행한다. shortDescription, fullDescription
        studyService.updateStudyDescription(study, studyDescriptionForm);
        attributes.addFlashAttribute("message", "스터디 소개를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/description";
    }

    // 배너 수정 로직
    @GetMapping("/banner")
    // 배너 요청이 들어오면 현재 계정정보와 경로와 모델을 파라미터 값으로 설정해준다.
    public String studyImageForm(@CurrentAccount Account account, @PathVariable String path, Model model) {
        // 스터디 객체에 스터디 서비스 로직의 getStudyToUpdate(account, path);로 처리된 반환값을 넣어준다
        // getStudyToUpdate(account, path); 경로와 아이디 값을 통해 매니저 체크하고 스터디 객체로 반환하는 로직
        Study study = studyService.getStudyToUpdate(account, path);
        // 모델에 계정과 매니저 검증된 스터디 객체를 담고
        model.addAttribute(account);
        model.addAttribute(study);
        // banner.html로 리턴한다.
        return "study/settings/banner";
    }

    @PostMapping("/banner")
    public String studyImageSubmit(@CurrentAccount Account account, @PathVariable String path,
                                   String image, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.updateStudyImage(study, image);
        attributes.addFlashAttribute("message", "스터디 이미지를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    // 배너 이미지 변경 요청이 들어오면
    @PostMapping("/banner/enable")
    // 계정정보와 경로정보를 파라미터로 하여
    public String enableStudyBanner(@CurrentAccount Account account, @PathVariable String path) {
        // 스터디 객체에 업데이트 권한을 체크한다.
        Study study = studyService.getStudyToUpdate(account, path);
        // 스터디서비스에서 enableStudyBanner(study); 메서드를 실행시킨다.
        // enableStudyBanner(study);는 Study.class에서 @Getter, @Setter로 정의했기 떄문에 외부에서 접근 가능!
        studyService.enableStudyBanner(study);
        // 이후, 리 다이렉트 해준다.
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    // enable로직의 반대
    @PostMapping("/banner/disable")
    public String disableStudyBanner(@CurrentAccount Account account, @PathVariable String path) {
        Study study = studyService.getStudyToUpdate(account, path);
        studyService.disableStudyBanner(study);
        return "redirect:/study/" + study.getEncodedPath() + "/settings/banner";
    }

    // tags 요청이 들어온다.
    // 현재 계정정보, 경로, 모델을 파라미터로 설정한다.
    @GetMapping("/tags")
    public String studyTagsForm(@CurrentAccount Account account, @PathVariable String path, Model model)
            throws JsonProcessingException {
        // 마찬가지로 업데이트 권한이 있는지 확인하고.
        Study study = studyService.getStudyToUpdate(account, path);
        // 모델에 객체들을 담는다.
        model.addAttribute(account);
        model.addAttribute(study);
        // Study.class에 ManyToMany로 정의해놓았다.
        // Tags 정보들을 가져와서 stream() 메서드로 뿌리는데
        // Tags.class에서 Tag를 key, value: 가져온 Title,를 리스트화 해서 allTagTitles에 할당
        model.addAttribute("tags", study.getTags().stream()
                .map(Tag::getTitle).collect(Collectors.toList()));
        List<String> allTagTitles = tagRepository.findAll().stream()
                .map(Tag::getTitle).collect(Collectors.toList());
        // 오브젝트 매퍼라는게 있는데 whitelist라는 곳에 태그들을 스트링으로 적는다.
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allTagTitles));
        // 애후 tags.html 리턴
        return "study/settings/tags";
    }

    // Post 요청이 클라어언트로부터 온다면
    @PostMapping("/tags/add")
    // @ResponseBody 어노테이션을 사용하여 자바 객체를 HTTP의 바디 내용(객체)으로 변환하여 클라이언트로 전송한다.
    @ResponseBody
    public ResponseEntity addTag(@CurrentAccount Account account, @PathVariable String path,
                                 // 요청 들어오면
                                 // 서버에서는 @RequestBody 어노테이션을 사용하여
                                 // HTTP 요청 본문에 담긴 값들을 자바 객체로 변환 시켜, 객체에 저장.
                                 // 즉, 요청 들어오면 HTTP의 바디부를 자바로 변환시켜서 저장하기 위한 어노테이션임.
                                 @RequestBody TagForm tagForm) {
        // 어김없이 매니저 체킹
        Study study = studyService.getStudyToUpdateTag(account, path);
        // 태그 객체에 태그서비스에서 findOrCreateNew(tagForm.getTagTitle()); 메서드를 실행한다.
        // 먼저 tagForm.tagTitle 값을 findOrCreateNew 메서드에 파라미터로 넘긴다.
        Tag tag = tagService.findOrCreateNew(tagForm.getTagTitle());
        // 스터디 서비스의 addTag 메서드 호출 (파라미터 값 : study, tag)
        studyService.addTag(study, tag);
        // 비동기식 처리 인듯하다.
        return ResponseEntity.ok().build();
    }

    // 삭제요청이 왔을 때
    @PostMapping("/tags/remove")
    // @ResponseBody는 자바객체를 http의 본문으로 변환하여 클라이언트에게 전송
    @ResponseBody
    public ResponseEntity removeTag(@CurrentAccount Account account, @PathVariable String path,
                                    @RequestBody TagForm tagForm) {
        // 매니저 체킹
        Study study = studyService.getStudyToUpdateTag(account, path);
        // 레파지토리에서 찾고 tag에 담는다.
        Tag tag = tagRepository.findByTitle(tagForm.getTagTitle());
        // 저장소에 없으면 badRequest().build();
        if (tag == null) {
            return ResponseEntity.badRequest().build();
        }
        // 저장소에 있으면 studyService.removeTag() 메서드 실행
        // 파라미터 : study, tag
        studyService.removeTag(study, tag);
        // 이후 다시 빌드!
        return ResponseEntity.ok().build();
    }

    // 활동 지역 변경 요청이 들어왔을 시
    @GetMapping("/zones")
    public String studyZonesForm(@CurrentAccount Account account, @PathVariable String path, Model model)
            throws JsonProcessingException {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);
        // 모
        model.addAttribute("zones", study.getZones().stream()
                .map(Zone::toString).collect(Collectors.toList()));
        List<String> allZones = zoneRepository.findAll().stream().map(Zone::toString).collect(Collectors.toList());
        model.addAttribute("whitelist", objectMapper.writeValueAsString(allZones));
        return "study/settings/zones";
    }

    @PostMapping("/zones/add")
    @ResponseBody
    public ResponseEntity addZone(@CurrentAccount Account account, @PathVariable String path,
                                  @RequestBody ZoneForm zoneForm) {
        Study study = studyService.getStudyToUpdateZone(account, path);
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        studyService.addZone(study, zone);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/zones/remove")
    @ResponseBody
    public ResponseEntity removeZone(@CurrentAccount Account account, @PathVariable String path,
                                     @RequestBody ZoneForm zoneForm) {
        Study study = studyService.getStudyToUpdateZone(account, path);
        Zone zone = zoneRepository.findByCityAndProvince(zoneForm.getCityName(), zoneForm.getProvinceName());
        if (zone == null) {
            return ResponseEntity.badRequest().build();
        }

        studyService.removeZone(study, zone);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/study")
    public String studySettingForm(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdate(account, path);
        model.addAttribute(account);
        model.addAttribute(study);
        return "study/settings/study";
    }

    @PostMapping("/study/publish")
    public String publishStudy(@CurrentAccount Account account, @PathVariable String path,
                               RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        studyService.publish(study);
        attributes.addFlashAttribute("message", "스터디를 공개했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/study/close")
    public String closeStudy(@CurrentAccount Account account, @PathVariable String path,
                             RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        studyService.close(study);
        attributes.addFlashAttribute("message", "스터디를 종료했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/recruit/start")
    public String startRecruit(@CurrentAccount Account account, @PathVariable String path, Model model,
                               RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        if (!study.canUpdateRecruiting()) {
            attributes.addFlashAttribute("message", "1시간 안에 인원 모집 설정을 여러번 변경할 수 없습니다.");
            return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
        }

        studyService.startRecruit(study);
        attributes.addFlashAttribute("message", "인원 모집을 시작합니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/recruit/stop")
    public String stopRecruit(@CurrentAccount Account account, @PathVariable String path, Model model,
                              RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdate(account, path);
        if (!study.canUpdateRecruiting()) {
            attributes.addFlashAttribute("message", "1시간 안에 인원 모집 설정을 여러번 변경할 수 없습니다.");
            return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
        }

        studyService.stopRecruit(study);
        attributes.addFlashAttribute("message", "인원 모집을 종료합니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/study/path")
    public String updateStudyPath(@CurrentAccount Account account, @PathVariable String path, String newPath,
                                  Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        if (!studyService.isValidPath(newPath)) {
            model.addAttribute(account);
            model.addAttribute(study);
            model.addAttribute("studyPathError", "해당 스터디 경로는 사용할 수 없습니다. 다른 값을 입력하세요.");
            return "study/settings/study";
        }

        studyService.updateStudyPath(study, newPath);
        attributes.addFlashAttribute("message", "스터디 경로를 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/study/title")
    public String updateStudyTitle(@CurrentAccount Account account, @PathVariable String path, String newTitle,
                                   Model model, RedirectAttributes attributes) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        if (!studyService.isValidTitle(newTitle)) {
            model.addAttribute(account);
            model.addAttribute(study);
            model.addAttribute("studyTitleError", "스터디 이름을 다시 입력하세요.");
            return "study/settings/study";
        }

        studyService.updateStudyTitle(study, newTitle);
        attributes.addFlashAttribute("message", "스터디 이름을 수정했습니다.");
        return "redirect:/study/" + study.getEncodedPath() + "/settings/study";
    }

    @PostMapping("/study/remove")
    public String removeStudy(@CurrentAccount Account account, @PathVariable String path, Model model) {
        Study study = studyService.getStudyToUpdateStatus(account, path);
        studyService.remove(study);
        return "redirect:/";
    }

}
