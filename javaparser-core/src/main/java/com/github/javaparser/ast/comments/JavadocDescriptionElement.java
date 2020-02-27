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
import com.github.javaparser.ast.AllFieldsConstructor;
import com.github.javaparser.ast.Node;

/**
 * A node inside a JavadocDescription. Could either be a text snippet or inline tag. 
 * 
 * @author Simon Sirak & David Johansson
 */
public abstract class JavadocDescriptionElement extends Node  {

    @AllFieldsConstructor
    public JavadocDescriptionElement() {
      super(null);
    }

    /**
     * Return the text content of the the description element.
     * 
     * @return string content
     */
    public abstract String toText();

    public JavadocDescriptionElement(TokenRange tokenRange) {
        super(tokenRange);
    }    
}