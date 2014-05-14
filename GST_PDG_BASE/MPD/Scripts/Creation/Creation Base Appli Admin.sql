GO
/****** Object:  Table [dbo].[UniteFormation]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UniteFormation](
	[Libelle] [varchar](200) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[LibelleCourt] [varchar](10) NOT NULL,
	[IdUniteFormation] [int] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_UniteFormation] PRIMARY KEY CLUSTERED 
(
	[IdUniteFormation] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Module]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Module](
	[Libelle] [varchar](200) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[PrixPublicEnCours] [float] NOT NULL,
	[LibelleCourt] [varchar](20) NOT NULL,
	[IdModule] [int] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_Module] PRIMARY KEY CLUSTERED 
(
	[IdModule] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Formation]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Formation](
	[CodeFormation] [char](8) NOT NULL,
	[LibelleLong] [varchar](200) NOT NULL,
	[LibelleCourt] [varchar](50) NOT NULL,
	[DureeEnHeures] [smallint] NOT NULL,
	[TauxHoraire] [float] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[CodeTitre] [char](8) NULL,
	[PrixPublicEnCours] [float] NOT NULL,
	[HeuresCentre] [smallint] NULL,
	[HeuresStage] [smallint] NULL,
	[SemainesCentre] [tinyint] NULL,
	[SemainesStage] [tinyint] NULL,
	[DureeEnSemaines] [tinyint] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[Archiver] [bit] NOT NULL,
 CONSTRAINT [PK_Formation] PRIMARY KEY CLUSTERED 
(
	[CodeFormation] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fonction]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fonction](
	[CodeFonction] [char](5) NOT NULL,
	[Libelle] [varchar](100) NULL,
 CONSTRAINT [PK_Fonction] PRIMARY KEY CLUSTERED 
(
	[CodeFonction] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Entreprise]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Entreprise](
	[CodeEntreprise] [int] NOT NULL,
	[RaisonSociale] [varchar](255) NOT NULL,
	[Adresse1] [varchar](500) NULL,
	[Adresse2] [varchar](500) NULL,
	[Adresse3] [varchar](500) NULL,
	[CodePostal] [char](5) NULL,
	[Ville] [varchar](100) NULL,
	[Telephone] [char](14) NULL,
	[Fax] [char](14) NULL,
	[SiteWeb] [varchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Observation] [varchar](max) NULL,
	[CodeTypeEntreprise] [char](5) NOT NULL,
	[CodeRegion] [char](2) NOT NULL,
	[CodeSecteur] [int] NOT NULL,
	[CodeOrganisme] [int] NULL,
 CONSTRAINT [PK_Entreprise] PRIMARY KEY CLUSTERED 
(
	[CodeEntreprise] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Stagiaire]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Stagiaire](
	[CodeStagiaire] [int] NOT NULL,
	[Civilite] [char](3) NULL,
	[Nom] [nvarchar](50) NULL,
	[Prenom] [nvarchar](50) NULL,
	[Adresse1] [varchar](500) NOT NULL,
	[Adresse2] [varchar](500) NULL,
	[Adresse3] [varchar](500) NULL,
	[Codepostal] [char](5) NULL,
	[Ville] [varchar](100) NULL,
	[TelephoneFixe] [char](14) NULL,
	[TelephonePortable] [char](14) NULL,
	[Email] [varchar](100) NULL,
	[DateNaissance] [smalldatetime] NULL,
	[CodeRegion] [char](2) NULL,
	[CodeNationalite] [char](2) NULL,
	[CodeOrigineMedia] [char](2) NULL,
	[DateDernierEnvoiDoc] [datetime] NULL,
	[DateCreation] [datetime] NULL,
	[Repertoire] [varchar](100) NULL,
	[Permis] [bit] NOT NULL,
	[Photo] [varchar](100) NULL,
	[EnvoiDocEnCours] [bit] NOT NULL,
	[Historique] [varchar](max) NULL,
 CONSTRAINT [PK_Stagiaire] PRIMARY KEY CLUSTERED 
(
	[CodeStagiaire] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Salle]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Salle](
	[CodeSalle] [varchar](5) NOT NULL,
	[Libelle] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Salle] PRIMARY KEY CLUSTERED 
(
	[CodeSalle] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Promotion]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Promotion](
	[CodePromotion] [char](8) NOT NULL,
	[Libelle] [varchar](200) NOT NULL,
	[Debut] [datetime] NOT NULL,
	[Fin] [datetime] NOT NULL,
	[CodeFormation] [char](8) NOT NULL,
	[PrixPublicAffecte] [float] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[PrixPECAffecte] [float] NOT NULL,
	[PrixFinanceAffecte] [float] NOT NULL,
 CONSTRAINT [PK_Promotion] PRIMARY KEY CLUSTERED 
(
	[CodePromotion] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[StagiaireParEntreprise]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[StagiaireParEntreprise](
	[CodeStagiaire] [int] NOT NULL,
	[CodeEntreprise] [int] NOT NULL,
	[DateLien] [datetime] NOT NULL,
	[CodeTypeLien] [char](5) NOT NULL,
	[DateDebutEnEts] [datetime] NULL,
	[DateFinEnEts] [datetime] NULL,
	[CodeFonction] [char](5) NULL,
	[Commentaire] [nvarchar](max) NULL,
	[NumLien] [int] NOT NULL,
 CONSTRAINT [PK_StagiaireParEntreprise] PRIMARY KEY CLUSTERED 
(
	[NumLien] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UniteParFormation]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UniteParFormation](
	[CodeFormation] [char](8) NOT NULL,
	[Position] [tinyint] NOT NULL,
	[Id] [int] NOT NULL,
	[IdUniteFormation] [int] NOT NULL,
 CONSTRAINT [PK_UniteParFormation] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Contact]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Contact](
	[CodeContact] [int] NOT NULL,
	[Nom] [varchar](50) NOT NULL,
	[Prenom] [varchar](50) NULL,
	[TelFixe] [char](14) NULL,
	[TelMobile] [char](14) NULL,
	[Fax] [char](14) NULL,
	[Email] [varchar](50) NULL,
	[CodeFonction] [char](5) NOT NULL,
	[Observation] [varchar](1000) NULL,
	[CodeEntreprise] [int] NOT NULL,
	[CodeImportance] [int] NOT NULL,
	[Archive] [bit] NOT NULL,
	[Civilite] [varchar](4) NULL,
 CONSTRAINT [PK_Contact] PRIMARY KEY CLUSTERED 
(
	[CodeContact] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ModuleParUnite]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ModuleParUnite](
	[Position] [tinyint] NOT NULL,
	[Id] [int] NOT NULL,
	[IdUnite] [int] NOT NULL,
	[IdModule] [int] NOT NULL,
 CONSTRAINT [PK_ModuleParUnite] PRIMARY KEY CLUSTERED 
(
	[Id] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Cours]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cours](
	[Debut] [datetime] NOT NULL,
	[Fin] [datetime] NOT NULL,
	[DureeReelleEnHeures] [smallint] NOT NULL,
	[CodePromotion] [char](8) NULL,
	[IdCours] [uniqueidentifier] NOT NULL,
	[PrixPublicAffecte] [float] NOT NULL,
	[DateCreation] [datetime] NOT NULL,
	[DateModif] [timestamp] NOT NULL,
	[IdModule] [int] NOT NULL,
	[LibelleCours] [varchar](50) NOT NULL,
	[DureePrevueEnHeures] [smallint] NOT NULL,
	[CodeSalle] [varchar](5) NULL,
	[CodeFormateur] [int] NULL,
 CONSTRAINT [PK_Cours] PRIMARY KEY CLUSTERED 
(
	[IdCours] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PlanningIndividuelFormation]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PlanningIndividuelFormation](
	[CodePlanning] [int] NOT NULL,
	[CodeStagiaire] [int] NOT NULL,
	[DateInscription] [datetime] NULL,
	[DateCreation] [datetime] NULL,
	[CodeTypeProfil] [int] NOT NULL,
	[CodeFormation] [char](8) NULL,
	[CodePromotion] [char](8) NULL,
	[DateModif] [timestamp] NOT NULL,
	[NumLien] [int] NULL,
 CONSTRAINT [PK_PlanningIndividuelFormation] PRIMARY KEY CLUSTERED 
(
	[CodePlanning] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PlanningIndividuelDetail]    Script Date: 02/05/2014 10:50:49 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PlanningIndividuelDetail](
	[CodePlanning] [int] NOT NULL,
	[IdCours] [uniqueidentifier] NOT NULL,
	[PrixCoursDevis] [float] NOT NULL,
	[PrixCoursPECDevis] [float] NULL,
	[PrixCoursFinanceDevis] [float] NULL,
	[Dispense] [bit] NOT NULL,
	[Inscrit] [bit] NOT NULL,
	[DebutCours] [datetime] NULL,
	[FinCours] [datetime] NULL,
	[HeuresReellesCours] [smallint] NULL,
 CONSTRAINT [PK_PlanningIndividuelDetail] PRIMARY KEY CLUSTERED 
(
	[CodePlanning] ASC,
	[IdCours] ASC
)WITH (PAD_INDEX  = OFF, STATISTICS_NORECOMPUTE  = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS  = ON, ALLOW_PAGE_LOCKS  = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  ForeignKey [FK_Contact_Entreprise]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Entreprise] FOREIGN KEY([CodeEntreprise])
REFERENCES [dbo].[Entreprise] ([CodeEntreprise])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Entreprise]
GO
/****** Object:  ForeignKey [FK_Contact_Fonction]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Contact]  WITH CHECK ADD  CONSTRAINT [FK_Contact_Fonction] FOREIGN KEY([CodeFonction])
REFERENCES [dbo].[Fonction] ([CodeFonction])
GO
ALTER TABLE [dbo].[Contact] CHECK CONSTRAINT [FK_Contact_Fonction]
GO
/****** Object:  ForeignKey [FK_Cours_Module]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Cours]  WITH CHECK ADD  CONSTRAINT [FK_Cours_Module] FOREIGN KEY([IdModule])
REFERENCES [dbo].[Module] ([IdModule])
GO
ALTER TABLE [dbo].[Cours] CHECK CONSTRAINT [FK_Cours_Module]
GO
/****** Object:  ForeignKey [FK_Cours_Promotion]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Cours]  WITH CHECK ADD  CONSTRAINT [FK_Cours_Promotion] FOREIGN KEY([CodePromotion])
REFERENCES [dbo].[Promotion] ([CodePromotion])
GO
ALTER TABLE [dbo].[Cours] CHECK CONSTRAINT [FK_Cours_Promotion]
GO
/****** Object:  ForeignKey [FK_Cours_Salle]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Cours]  WITH CHECK ADD  CONSTRAINT [FK_Cours_Salle] FOREIGN KEY([CodeSalle])
REFERENCES [dbo].[Salle] ([CodeSalle])
GO
ALTER TABLE [dbo].[Cours] CHECK CONSTRAINT [FK_Cours_Salle]
GO
/****** Object:  ForeignKey [FK_ModuleParUnite_Module]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[ModuleParUnite]  WITH CHECK ADD  CONSTRAINT [FK_ModuleParUnite_Module] FOREIGN KEY([IdModule])
REFERENCES [dbo].[Module] ([IdModule])
GO
ALTER TABLE [dbo].[ModuleParUnite] CHECK CONSTRAINT [FK_ModuleParUnite_Module]
GO
/****** Object:  ForeignKey [FK_ModuleParUnite_UniteParFormation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[ModuleParUnite]  WITH CHECK ADD  CONSTRAINT [FK_ModuleParUnite_UniteParFormation] FOREIGN KEY([IdUnite])
REFERENCES [dbo].[UniteParFormation] ([Id])
GO
ALTER TABLE [dbo].[ModuleParUnite] CHECK CONSTRAINT [FK_ModuleParUnite_UniteParFormation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelDetail_Cours]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[PlanningIndividuelDetail]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelDetail_Cours] FOREIGN KEY([IdCours])
REFERENCES [dbo].[Cours] ([IdCours])
GO
ALTER TABLE [dbo].[PlanningIndividuelDetail] CHECK CONSTRAINT [FK_PlanningIndividuelDetail_Cours]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelDetail_PlanningIndividuelFormation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[PlanningIndividuelDetail]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelDetail_PlanningIndividuelFormation] FOREIGN KEY([CodePlanning])
REFERENCES [dbo].[PlanningIndividuelFormation] ([CodePlanning])
GO
ALTER TABLE [dbo].[PlanningIndividuelDetail] CHECK CONSTRAINT [FK_PlanningIndividuelDetail_PlanningIndividuelFormation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_Formation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_Formation]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_Stagiaire]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_Stagiaire]
GO
/****** Object:  ForeignKey [FK_PlanningIndividuelFormation_StagiaireParEntreprise]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[PlanningIndividuelFormation]  WITH CHECK ADD  CONSTRAINT [FK_PlanningIndividuelFormation_StagiaireParEntreprise] FOREIGN KEY([NumLien])
REFERENCES [dbo].[StagiaireParEntreprise] ([NumLien])
GO
ALTER TABLE [dbo].[PlanningIndividuelFormation] CHECK CONSTRAINT [FK_PlanningIndividuelFormation_StagiaireParEntreprise]
GO
/****** Object:  ForeignKey [FK_Promotion_Formation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[Promotion]  WITH CHECK ADD  CONSTRAINT [FK_Promotion_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[Promotion] CHECK CONSTRAINT [FK_Promotion_Formation]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Entreprise]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Entreprise] FOREIGN KEY([CodeEntreprise])
REFERENCES [dbo].[Entreprise] ([CodeEntreprise])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Entreprise]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Fonction]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Fonction] FOREIGN KEY([CodeFonction])
REFERENCES [dbo].[Fonction] ([CodeFonction])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Fonction]
GO
/****** Object:  ForeignKey [FK_StagiaireParEntreprise_Stagiaire]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[StagiaireParEntreprise]  WITH CHECK ADD  CONSTRAINT [FK_StagiaireParEntreprise_Stagiaire] FOREIGN KEY([CodeStagiaire])
REFERENCES [dbo].[Stagiaire] ([CodeStagiaire])
GO
ALTER TABLE [dbo].[StagiaireParEntreprise] CHECK CONSTRAINT [FK_StagiaireParEntreprise_Stagiaire]
GO
/****** Object:  ForeignKey [FK_UniteParFormation_Formation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[UniteParFormation]  WITH CHECK ADD  CONSTRAINT [FK_UniteParFormation_Formation] FOREIGN KEY([CodeFormation])
REFERENCES [dbo].[Formation] ([CodeFormation])
GO
ALTER TABLE [dbo].[UniteParFormation] CHECK CONSTRAINT [FK_UniteParFormation_Formation]
GO
/****** Object:  ForeignKey [FK_UniteParFormation_UniteFormation]    Script Date: 02/05/2014 10:50:49 ******/
ALTER TABLE [dbo].[UniteParFormation]  WITH CHECK ADD  CONSTRAINT [FK_UniteParFormation_UniteFormation] FOREIGN KEY([IdUniteFormation])
REFERENCES [dbo].[UniteFormation] ([IdUniteFormation])
GO
ALTER TABLE [dbo].[UniteParFormation] CHECK CONSTRAINT [FK_UniteParFormation_UniteFormation]
GO
