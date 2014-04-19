package com.gh.MobyUniver;


import android.graphics.Bitmap;

public class CoursesItems {
    String id, courseName, video, courseIcon;


    public CoursesItems(String id, String courseName, String courseIcon, String video) {
        this.id = id;
        this.courseName = courseName;
        this.courseIcon = courseIcon;
        this.video = video;

    }

    public String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseIcon() {
        return courseIcon;
    }

    public String getVideo() {
        return video;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void  setCourseIcon(String courseIcon){this.courseIcon = courseIcon;}

    public void setVideo(String video) {
        this.video = video;
    }


}
