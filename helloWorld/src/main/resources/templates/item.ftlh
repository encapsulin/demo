<style>
.title {
background-color:grey;
color:white;
}
th {
background-color:silver;
}
</style>

<form method=post >
<table border=0>
<tr><td colspan=3 align=center class="title"><b>Items</b></td></tr>

<tr>
	<th>id</th>
	<th>title</th>
	<th>action</th>
</tr>

<#list items as item> 
<tr>
	<td>${item.id}</td>
	<td>${item.title}</td>
	<td><a href="/del?id=${item.id}">[del]</a></td>
</tr>
</#list>

<tr><td colspan=3 class="title"></td></tr> 

<tr>
<td></td>
<td><input type=text name="title"></td>
<td><input type=submit value="Add"></td>

</td></tr>



</table>

</form>

<!-- if itemz??>
<tr>
	<td></td>
	<td>none</td>
	<td></td>
</tr>
<else>
hi
</if -->