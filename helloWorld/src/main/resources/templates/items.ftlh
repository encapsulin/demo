<style>
.title {
background-color:grey;
color:white;
}
th {
background-color:silver;
}
</style>
<a href="/item/edit">Add new</a>

<form method=post >
<table border=0>
<tr><td colspan=4 align=center class="title"><b>Items</b></td></tr>

<tr>
	<th>id</th>
	<th>title</th>
	<th>updated</th>
	<th>action</th>
</tr>

<#list items as item> 
<tr>
	<td>${item.id}</td>
	<td>${item.title}</td>
	<td>${item.updated}</td>
	<td><a href="/item/edit?id=${item.id?long?c}">[edit]</a>
	<a href="/item/del?id=${item.id?long?c}">[del]</a></td>
</tr>
</#list>

<tr><td colspan=4 class="title"></td></tr> 

<tr>
	<td colspan=4>
<#list pages as page> 
<a href="?page=${page}">${page}</a>
</#list>
</td>
</tr>

</table>

</form>

<!-- 
<tr>
<td></td>
<td><input type=text name="title"></td>
<td><input type=submit value="Add"></td>

</td></tr>
if itemz??>
<tr>
	<td></td>
	<td>none</td>
	<td></td>
</tr>
<else>
hi
</if -->