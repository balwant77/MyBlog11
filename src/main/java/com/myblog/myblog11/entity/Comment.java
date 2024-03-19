package com.myblog.myblog11.entity;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Entity
    @Table(name = "comments")
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Comment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        private String text;
        private String email;

        @ManyToOne
        @JoinColumn(name = "post_id")
        private Post post;
        //This means that a comment is associated with exactly one post, and this relationship is mapped to a database column named "post_id."
        //The @JoinColumn annotation specifies the name of the column in the Comment table that will be used to store the foreign key referencing the corresponding Post.
    }


