      <%@ page import="java.util.List, models.Products" %>
      <%@ page contentType="text/html;charset=UTF-8" language="java" %>


      <%  List<Products>  liste = (List<Products>)request.getAttribute("liste");%>
      <%  String action_ajout = (String)request.getAttribute("action_ajout");%>
      <%  String action_suppr = (String)request.getAttribute("action_suppr");%>
      <%  String action_modif = (String)request.getAttribute("action_modif");%>
      <%  String action_add = (String)request.getAttribute("action_add");%>
      <%  Products prod = (Products)request.getAttribute("products");%>
      <%  String erreur_add = (String)request.getAttribute("erreur_add");%>
      <%  String erreur_ajout = (String)request.getAttribute("erreur_ajout");%>
      <%  String erreur_modif = (String)request.getAttribute("erreur_modif");%>
      <%  String erreur_suppr = (String)request.getAttribute("erreur_suppr");%>
     <html>
        <head>
          <title>Accueil</title>
          <link rel="stylesheet" href="bootstrap-4.0.0/dist/css/bootstrap.min.css">
          <script src="bootstrap-4.0.0/dist/js/bootstrap.min.js"></script>
        </head>
        <body>
        <nav class="navbar navbar-expand-lg navbar-light" style="background-color: #E6F7FF">
          <a class="navbar-brand" href="#">Gestion de stock</a>
          <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                  aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>

          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
              <li class="nav-item active">
                <a class="nav-link" href="Update">Accueil<span class="sr-only">(current)</span></a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="Historique">Historique</a>
              </li>
            </ul>
            </form>
          </div>
        </nav>
      <%--
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      +++++++++++++++++++++++++++++++++++++Modaladd+++++++++++++++++++++++++++++++++++++++++
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      --%>
        <%if(action_add == "true"){%>
        <div class="modal" id="reg-modaladd" tabindex="-1" role="dialog" style="display: block;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Modification de produit</h5>
                <button type="button" class="close" id="closeadd" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form method="post" action="AddServlet">
                  <div class="form-group">
                    <label>Nom du produit</label>
                    <input type="text" class="form-control" name="NameAdd" value="" placeholder="Nom du produit">
                  </div>
                  <div class="form-group">
                    <label>Nombre de produit</label>
                    <input type="text" class="form-control" name="NumberAdd" value="" placeholder="Nombre de produit">
                  </div>
                  <div class="form-group">
                    <label>Description du produit</label>
                    <input type="text" class="form-control" name="DescriptionAdd" placeholder="Facultatif pour la description du produit" value="">
                  </div>
                  <% if (erreur_add != null){%>
                  <p class="alert alert-danger"><%= erreur_add %></>
                  <% } %>
                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                    <button type="button" class="btn btn-secondary" id="closeModalFooterBtnadd">Close</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <script>
          document.addEventListener('DOMContentLoaded', function() {
            var modal = document.getElementById('reg-modaladd');
            var closeBtn = document.getElementById('closeadd');
            var closeModalFooterBtn = document.getElementById('closeModalFooterBtnadd');

            // Fermer le modal lorsqu'on clique sur <span> (x) ou sur le bouton de fermeture
            closeBtn.onclick = function() {
              modal.style.display = 'none';
            }

            closeModalFooterBtn.onclick = function() {
              modal.style.display = 'none';
            }

            // Fermer le modal lorsqu'on clique en dehors du modal
            window.onclick = function(event) {
              if (event.target === modal) {
                modal.style.display = 'none';
              }
            }
          });
        </script>
        <%
          }
        %>
        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++/Modaladd+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>


        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++Modalajout+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>
        <%if(action_ajout == "true"){%>
        <div class="modal" id="reg-modalajout" tabindex="-1" role="dialog" style="display: block;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Ajout du produit <%= prod.getName().toLowerCase() %></h5>
                <button type="button" class="close" id="closeAjout" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form method="post" action="AjouterServlet">
                  <div class="form-group">
                    <label>Nombre de produit</label>
                    <input type="text" class="form-control" name="nombre_ajouter" placeholder="Nombre a ajouter">
                  </div>
                  <div class="form-group">
                    <label>Description du produit</label>
                    <input type="text" class="form-control" name="description_ajouter" placeholder="Facultatif">
                  </div>
                  <% if (erreur_ajout != null){%>
                  <p class="alert alert-danger"><%= erreur_ajout %></>
                  <% } %>
                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                    <button type="button" class="btn btn-secondary" id="closeModalFooterBtnajout">Close</button>
                    <input type="hidden" name="id_ajouter" value="<%= prod.getId() %>">
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <script>
          document.addEventListener('DOMContentLoaded', function() {
            var modal = document.getElementById('reg-modalajout');
            var closeBtn = document.getElementById('closeAjout');
            var closeModalFooterBtn = document.getElementById('closeModalFooterBtnajout');

            // Fermer le modal lorsqu'on clique sur <span> (x) ou sur le bouton de fermeture
            closeBtn.onclick = function() {
              modal.style.display = 'none';
            }

            closeModalFooterBtn.onclick = function() {
              modal.style.display = 'none';
            }

            // Fermer le modal lorsqu'on clique en dehors du modal
            window.onclick = function(event) {
              if (event.target === modal) {
                modal.style.display = 'none';
              }
            }
          });
        </script>
        <%
          }
        %>

        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++/Modalajout+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>







        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++ModalSupprimer+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>
        <%if(action_suppr == "true"){%>
        <div class="modal" id="reg-modalsuppr" tabindex="-1" role="dialog" style="display: block;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Suppression du produit <%= prod.getName().toLowerCase() %></h5>
                <button type="button" class="close" id="closeSuppr" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form method="post" action="SupprimerServlet">
                  <div class="form-group">
                    <label>Nombre de produit</label>
                    <input type="text" class="form-control" name="nombre_supprimer" placeholder="Nombre a ajouter">
                  </div>
                  <div class="form-group">
                    <label>Description du produit</label>
                    <input type="text" class="form-control" name="description_supprimer" placeholder="Facultatif">
                  </div>
                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Supprimer</button>
                    <button type="button" class="btn btn-secondary" id="closeModalFooterBtnSuppr">Close</button>
                    <input type="hidden" name="id_suppr" value="<%= prod.getId() %>">
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <script>
          document.addEventListener('DOMContentLoaded', function() {
            var modal = document.getElementById('reg-modalsuppr');
            var closeBtn = document.getElementById('closeSuppr');
            var closeModalFooterBtn = document.getElementById('closeModalFooterBtnSuppr');

            // Fermer le modal lorsqu'on clique sur <span> (x) ou sur le bouton de fermeture
            closeBtn.onclick = function() {
              modal.style.display = 'none';
            }

            closeModalFooterBtn.onclick = function() {
              modal.style.display = 'none';
            }

            // Fermer le modal lorsqu'on clique en dehors du modal
            window.onclick = function(event) {
              if (event.target === modal) {
                modal.style.display = 'none';
              }
            }
          });
        </script>
        <%
          }
        %>


        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++/ModalSupprimer+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>





        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++ModalModifier+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>
        <%if(action_modif == "true"){%>
        <div class="modal" id="reg-modalmodif" tabindex="-1" role="dialog" style="display: block;">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
              <div class="modal-header">
                <h5 class="modal-title">Modification de produit</h5>
                <button type="button" class="close" id="closemodif" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </div>
              <div class="modal-body">
                <form method="post" action="ModifierServlet">
                  <div class="form-group">
                    <label>Nom du produit</label>
                    <input type="text" class="form-control" name="nom_modif" value="<%= prod.getName() %>" placeholder="Nombre a ajouter">
                  </div>
                  <div class="form-group">
                    <label>Nombre de produit</label>
                    <input type="text" class="form-control" name="nombre_modif" value="<%= prod.getNumber()%>" placeholder="Nombre a ajouter">
                  </div>
                  <div class="form-group">
                    <label>Description du produit</label>
                    <input type="text" class="form-control" name="description_modif" placeholder="Facultatif" value="<%=(prod.getDescription() != "") ? prod.getDescription() : "" %>">
                  </div>
                  <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">Modifier</button>
                    <button type="button" class="btn btn-secondary" id="closeModalFooterBtnmodif">Close</button>
                    <input type="hidden" name="id_modif" value="<%= prod.getId() %>">
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>

        <script>
          document.addEventListener('DOMContentLoaded', function() {
            var modal = document.getElementById('reg-modalmodif');
            var closeBtn = document.getElementById('closemodif');
            var closeModalFooterBtn = document.getElementById('closeModalFooterBtnmodif');

            // Fermer le modal lorsqu'on clique sur <span> (x) ou sur le bouton de fermeture
            closeBtn.onclick = function() {
              modal.style.display = 'none';
            }

            closeModalFooterBtn.onclick = function() {
              modal.style.display = 'none';
            }

            // Fermer le modal lorsqu'on clique en dehors du modal
            window.onclick = function(event) {
              if (event.target === modal) {
                modal.style.display = 'none';
              }
            }
          });
        </script>
        <%
          }
        %>


        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++/ModalSupprimer+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        --%>
        <%--
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      +++++++++++++++++++++++++++++++++++++liste++++++++++++++++++++++++++++++++++++++++++++
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
      --%>
        <div class="container">

          <div class="d-flex w-100 justify-content-end">
            <a class="btn btn-success" href="AddServlet"  id="openModaladd">Nouvelle produit</a>
          </div>
          <% if (erreur_ajout != null){%>
          <p class="alert alert-danger"><%= erreur_ajout %></p>
          <% } %>
          <% if (erreur_modif != null){%>
          <p class="alert alert-danger"><%= erreur_modif %></p>
          <% } %>
          <% if (erreur_suppr != null){%>
          <p class="alert alert-danger"><%= erreur_suppr %></p>
          <% } %>

            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Produit</th>
                  <th>Nombre</th>
                </tr>

              </thead>
              <tbody>
              <%
                  for(Products p:liste){
              %>

              <tr>
                <td><%= p.getName() %></td>
                <td><%= p.getNumber() %></td>
                <td>
                  <div class="d-flex gap-4 w-100 justify-content-end">
                    <div class="me-3"><a class="btn btn-success " href="AjouterServlet?id=<%=p.getId()%>">Ajouter</a></div>
                    <div class="me-3"><a class="btn btn-primary" href="ModifierServlet?id=<%=p.getId()%>">Modifier</a></div>
                    <div class="me-3"><a class="btn btn-danger" href="SupprimerServlet?id=<%=p.getId()%>">Supprimer</a></div>
                  </div>
                </td>
              </tr>

              <% } %>
              </tbody>
            </table>
          </div>
        <%--
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    +++++++++++++++++++++++++++++++++++++/liste++++++++++++++++++++++++++++++++++++++++++++
    ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    --%>

        </body>
        <%--
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        +++++++++++++++++++++++++++++++++++++ScriptModaladd+++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        --%>


        <script>
          // Get elements
          const openModalBtn = document.getElementById('openModaladd');
          const closeModalBtn = document.getElementById('closeModalBtn');
          const closeModalFooterBtn = document.getElementById('closeModalFooterBtn');
          const modal = document.getElementById('reg-modal');

          // Function to show the modal
          function showModal() {
            modal.style.display = 'block';
            modal.classList.add('show');
            document.body.style.overflow = 'hidden'; // Prevent scrolling when modal is open
          }

          // Function to hide the modal
          function hideModal() {
            modal.style.display = 'none';
            modal.classList.remove('show');
            document.body.style.overflow = ''; // Restore scrolling when modal is closed
          }

          // Event listeners
          openModalBtn.addEventListener('click', showModal);
          closeModalBtn.addEventListener('click', hideModal);
          closeModalFooterBtn.addEventListener('click', hideModal);

          // Optional: Close the modal when clicking outside of it
          window.onclick = function(event) {
            if (event.target === modal) {
              hideModal();
            }
          }
        </script>
      </html>
