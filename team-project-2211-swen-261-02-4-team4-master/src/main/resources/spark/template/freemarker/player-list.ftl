<head>
</head>
<body>
<#if currentUser??>
  <form action="/game" METHOD="get">
    <button type="button">Play</button>
  </form>
  <p>
      List of Players:

  </p>

    <#list players as player>
        <h3>${player}</h3>

    </#list>
  <#else>
  <p>
    Number of Players: ${playernum}
  </p>
</#if>
</body>