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

import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.observer.ObservableProperty;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * A JavadocSnippet node containing string content.
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocSnippet extends JavadocDescriptionElement {
    
    private String text;

    public JavadocSnippet() {
        this(null, "");
    }

    @AllFieldsConstructor
    public JavadocSnippet(String text) {
        this(null, text);
    }
    
    public JavadocSnippet(TokenRange tokenRange, String text) {
        super(tokenRange);
        setText(text);
    } 

    /**
     * Set text of current node.
     * 
     * @param description string text 
     * @return new version of current object
     */
    public JavadocSnippet setText(String text) {
        assertNotNull(text);
        if (text == this.text) {
            return this;
        }
        notifyPropertyChange(ObservableProperty.CONTENT, this.text, text);
        this.text = text;
        return this;
    }

    /**
     * Get string text of snippet
     * 
     * @return string text 
     */
    public String getText() {
        return text;
    }

    /**
     * Return the text content of the the snippet.
     * 
     * @return string content
     */
    public String toText() {
        return text;
    }    

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {
    }
}