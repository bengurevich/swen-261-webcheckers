<!DOCTYPE html>

<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
  <meta http-equiv="refresh" content="10">
  <title>Web Checkers | ${title}</title>
  <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>

<body>
<div class="page">

  <h1>Web Checkers | ${title}</h1>

  <!-- Provide a navigation bar -->
  <#include "nav-bar.ftl" />

  <div class="body">

    <!-- Provide a message to the user, if supplied. -->
    <#include "message.ftl" />
    <!-- TODO: future content on the Home:
            to start games,
            spectating active games,
            or replay archived games
    -->

    <#if currentUser??>
      <p>
          List of Players:

      </p>

      <ul style="list-style: none;">
        <#list players as player>
          <#if playerName != player>
            <form action="/game" method="GET">
              <li style="margin-bottom: 20px;">
                ${player}
                  <button type="submit" name="challenge" value=${player}>Challenge</button>
              </li>
            </form>
              <form action="/spectator/game" method="GET">
                  <li style="margin-bottom: 10px;">
                      <button style="margin-left: 5%;" type="submit" name="spectate" value=${player}>Spectate</button>
                  </li>
              </form>
            <#--  <form action="/game" method="GET">
              <button type="submit" name="white" value="${player}">${player}</button>
            </form>  -->
          </#if>
        </#list>
      </ul>
      
      <#else>
      <p>
        Number of Active Players: ${playernum}
      </p>
    </#if>

  </div>

</div>
</body>

</html>
