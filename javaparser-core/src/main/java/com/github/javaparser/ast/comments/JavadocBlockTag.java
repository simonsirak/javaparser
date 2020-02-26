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

import com.github.javaparser.TokenRange;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.visitor.GenericVisitor;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.ast.observer.ObservableProperty;

/**
 * A JavaDoc block tag. Different tag types are defined by the BlockTagType enum. 
 * 
 * @author Simon Sirak & David Johansson
 */
public class JavadocBlockTag extends Node { 

    private JavadocDescription description;
    private BlockTagType type;

    public enum BlockTagType {
        AUTHOR,
        DEPRECATED,
        EXCEPTION,
        PARAM,
        RETURN,
        SEE,
        SERIAL,
        SERIAL_DATA,
        SERIAL_FIELD,
        SINCE,
        THROWS,
        VERSION,
        UNKNOWN;
    }

    protected JavadocBlockTag(TokenRange tokenRange, JavadocDescription description, BlockTagType type) {
        super(tokenRange);
        setDescription(description);
        setType(type);
    }

    /**
     * Set the description of the block tag.
     *   
     * @param description describes the tag
     * @return new version of current object
     */
    public JavadocBlockTag setDescription(JavadocDescription description) {         
        if (description == this.description) {            
            return this;
        }
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.description, description);
        if (this.description != null)
            this.description.setParentNode(null);
        this.description = description;
        setAsParentNodeOf(description);
        return this;
    }

    /**
     * Set the type of the block tag.
     *      
     * @param type the block tag type
     * @return new version of current object
     */
    public JavadocBlockTag setType(BlockTagType type) {         
        if(type == null) {
            this.type = BlockTagType.UNKNOWN;
        }        
        notifyPropertyChange(ObservableProperty.ANNOTATIONS, this.type, type);
        this.type = type;        
        return this;
    }

    @Override
    public <R, A> R accept(GenericVisitor<R, A> v, A arg) {
        return null;
    }

    @Override
    public <A> void accept(VoidVisitor<A> v, A arg) {

    }
}
