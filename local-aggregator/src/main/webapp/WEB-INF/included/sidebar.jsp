<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/included/taglibs.jsp"%>
<div class="sidebar" id="sidebar">
<form action="search-results.html" method="GET" class="search-form">
    <div class="search-pane">
        <input type="text" name="search" placeholder="Search here...">
        <button type="submit"><i class="icon-search"></i></button>
    </div>
</form>

<ul class="nav nav-list">
<li>
    <a href="javascript:;">
        <i class="icon-dashboard"></i>
        <span class="menu-text-in"> Dashboard </span>
    </a>
</li>

<li>
    <a href="#" class="dropdown-toggle">
        <i class="icon-desktop"></i>
        <span class="menu-text-in"> UI Elements </span>

        <b class="arrow icon-angle-down"></b>
    </a>

    <ul class="submenu">
        <li>
            <a href="${ctx}/ace/elements.jsp">
                <i class="icon-double-angle-right"></i>
                Elements
            </a>
        </li>

        <li>
            <a href="${ctx}/ace/buttons.jsp">
                <i class="icon-double-angle-right"></i>
                Buttons &amp; Icons
            </a>
        </li>

        <li>
            <a href="treeview.jsp">
                <i class="icon-double-angle-right"></i>
                Treeview
            </a>
        </li>

        <li>
            <a href="#" class="dropdown-toggle">
                <i class="icon-double-angle-right"></i>

                Three Level Menu
                <b class="arrow icon-angle-down"></b>
            </a>

            <ul class="submenu">
                <li>
                    <a href="#">
                        <i class="icon-leaf"></i>
                        Item #1
                    </a>
                </li>

                <li>
                    <a href="#" class="dropdown-toggle">
                        <i class="icon-pencil"></i>

                        4th level
                        <b class="arrow icon-angle-down"></b>
                    </a>

                    <ul class="submenu">
                        <li>
                            <a href="#">
                                <i class="icon-plus"></i>
                                Add Product
                            </a>
                        </li>

                        <li>
                            <a href="#">
                                <i class="icon-eye-open"></i>
                                View Products
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
</li>

<li class="active open">
    <a href="#" class="dropdown-toggle">
        <i class="icon-file-alt"></i>

                                    <span class="menu-text-in">
                                        Other Pages
                                        <span class="badge badge-primary ">4</span>
                                    </span>

        <b class="arrow icon-angle-down"></b>
    </a>

    <ul class="submenu">
        <li class="active">
            <a href="error-404.jsp">
                <i class="icon-double-angle-right"></i>
                Error 404
            </a>
        </li>

        <li>
            <a href="error-500.jsp">
                <i class="icon-double-angle-right"></i>
                Error 500
            </a>
        </li>

        <li>
            <a href="grid.jsp">
                <i class="icon-double-angle-right"></i>
                Grid
            </a>
        </li>

        <li>
            <a href="blank.jsp">
                <i class="icon-double-angle-right"></i>
                Blank Page
            </a>
        </li>
    </ul>
</li>
</ul>
<!--/.nav-list-->

<div class="sidebar-collapse" id="sidebar-collapse">
    <i class="icon-double-angle-left"></i>
</div>
</div>
<!--./sidebar-->