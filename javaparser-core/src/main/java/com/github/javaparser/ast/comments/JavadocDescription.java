/*
 * Copyright (C) 2007-2010 Júlio Vilmar Gesser.
 * Copyright (C) 2011, 2013-2019 The JavaParser Team.
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
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.observer.ObservableProperty;
import static com.github.javaparser.utils.Utils.assertNotNull;

/**
 * A JavaDoc description.
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocDescription extends Node {    
    
    private NodeList<JavadocDescriptionElement> elements;   

    public JavadocDescription() {
        this(null, new NodeList<JavadocDescriptionElement>());
    }

    @AllFieldsConstructor
    public JavadocDescription(NodeList<JavadocDescriptionElement> elements) {
        this(null, elements);
    }

    public JavadocDescription(TokenRange tokenRange, NodeList<JavadocDescriptionElement> elements){
        super(tokenRange);        
        setElements(elements);
    }          

    /**
     * Set elements of current description such as text snippet or inline tags.
     * 
     * @param elements list containing all elements 
     * @return new version of current object
     */
    public JavadocDescription setElements(NodeList<JavadocDescriptionElement> elements) {
        assertNotNull(elements);
        if (elements == this.elements) {
            return this;
        }        
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.elements, elements);
        if (this.elements != null)
            this.elements.setParentNode(null);
        this.elements = elements;
        setAsParentNodeOf(elements);
        return this;
    }    

    /**
     * Get all JavaDoc elements
     * 
     * @return embedded JavaDoc elements
     */
    public NodeList<JavadocDescriptionElement> getElements() {
        return elements;
    }

    /**
     * Get content of description
     * 
     * @return string content
     */
    public String toText() {
        StringBuilder sb = new StringBuilder();
        elements.forEach(e -> sb.append(e.toText()));
        return sb.toString();
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }
}
