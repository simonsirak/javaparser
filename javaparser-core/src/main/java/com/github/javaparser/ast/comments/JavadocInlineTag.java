/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2020 The JavaParser Team.
 *
 * This file is part of JavaParser.
 *
 * JavaParser can be used either under the terms of
 * a) the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * b) the terms of the Apache License
 *
 * You should have received a copy of both licenses in LICENCE.LGPL and
 * LICENCE.APACHE. Please refer to those files for details.
 *
 * JavaParser is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 */
package com.github.javaparser.ast.comments;

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.observer.ObservableProperty;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * A JavaDoc inlinde tag node.
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocInlineTag extends JavadocDescriptionElement {
    
    private InlineTagType type;
    private String content;

    public enum InlineTagType {
        CODE,
        DOC_ROOT,
        INHERIT_DOC,
        LINK,
        LINKPLAIN,
        LITERAL,
        VALUE,
        SYSTEM_PROPERTY,
        UNKNOWN;

        public String toString() {
            return this.name().toLowerCase();
        }
    }    

    public JavadocInlineTag() {
        this(null, InlineTagType.UNKNOWN, "");
    }

    @AllFieldsConstructor
    public JavadocInlineTag(InlineTagType type, String content) {
        this(null, type, content);
    }

    public JavadocInlineTag(TokenRange tokenRange, InlineTagType type, String content) {
        super(tokenRange);        
        setType(type);
        setContent(content);
    }

    /**
     * Set type of inline tag.
     * 
     * @param type type of tag 
     * @return new version of current object
     */
    public JavadocInlineTag setType(InlineTagType type) {        
        assertNotNull(type);
        if(type == this.type) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.CONTENT, this.type, type);
        this.type = type;
        return this;
    }

    /**
     * Set content of inline tag.
     * 
     * @param type content of tag 
     * @return new version of current object
     */
    public JavadocInlineTag setContent(String content) {        
        assertNotNull(content);
        if(content == this.content) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.CONTENT, this.content, content);
        this.content = content;
        return this;
    }

    /**
     * Return type of inline tag.
     * 
     * @return type of tag
     */
    public InlineTagType getType() {
        return type;
    }

     /**
     * Return string description of inline tag.
     * 
     * @return description of tag
     */
    public String getString() {
        return content;
    }

    /**
     * Return the text content of the the inline tag.
     * 
     * @return string content
     */
    public String toText() {    
        return "{@" + type.toString() + content +"}";
    }


    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
    }
}
