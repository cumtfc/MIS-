package com.github.cumtfc.srs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.cumtfc.srs.po.course.Course;
import com.github.cumtfc.srs.po.section.Section;
import com.github.cumtfc.srs.po.student.Student;
import com.github.cumtfc.srs.po.transcript.Transcript;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public class TestWithoutSpring {

    @Test
    public void testTranscript() {
        Course course = new Course();
        course.setId(1);
        course.setCourseName("Java");
        course.setCredit(1);
        course.setCourseSn("EC001");

        Course course2 = new Course();
        course2.setId(2);
        course2.setCourseName("JavaWeb");
        course2.setCredit(1);
        course2.setCourseSn("EC002");

        ArrayList<Course> prevCourses = new ArrayList<>();
        prevCourses.add(course2);

        course.setPrevCourses(prevCourses);

        Section section = new Section();
        section.setCapacity(100);
        section.setCourse(course);
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(section);
        course.setSections(sectionList);

        Student student = new Student();
        student.setId(21);
        student.setTranscripts(new ArrayList<>());
        List<Course> studyPlan = new ArrayList<>();
//        studyPlan.add(course);
        student.setStudyPlan(studyPlan);

        Transcript transcript = new Transcript(section, student);
        System.out.println(transcript.canChoose());
    }

    @Test
    public void testJackson() {
        ObjectMapper mapper = new ObjectMapper();
        List<Course> list = new ArrayList<>();
        Course course = new Course();
        course.setId(1);
        Course course2 = new Course();
        course.setId(2);
        list.add(course);
        list.add(course2);
        try {
            JsonNode jsonNode = mapper.convertValue(list, ArrayNode.class);
            for (JsonNode node : jsonNode) {
                ObjectNode objectNode = (ObjectNode) node;
                objectNode.put("extraData", "helloWorld");
            }
            mapper.writeValue(System.out, jsonNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}